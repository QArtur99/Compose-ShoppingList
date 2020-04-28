package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.ui.data.model.ProductUi
import com.artf.data.repository.ShoppingListRepository
import com.artf.shoppinglistcompose.ui.data.mapper.asDomainModel
import com.artf.shoppinglistcompose.ui.data.mapper.asUiModel
import com.artf.shoppinglistcompose.ui.data.model.MutableScreenState
import com.artf.shoppinglistcompose.ui.data.model.ScreenState
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.data.status.Screen
import com.artf.shoppinglistcompose.util.ShoppingListType
import kotlinx.coroutines.launch

class SharedViewModel constructor(
    private val backStack: ScreenBackStackImpl,
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel(), ScreenBackStack by backStack {

    private val _currentScreen = backStack.getCurrentScreen()
    private val currentScreen: LiveData<Screen> = _currentScreen

    private val _updateShoppingListLoading = MutableLiveData<Boolean>()
    private val updateShoppingListLoading: LiveData<Boolean> = _updateShoppingListLoading

    private val _deleteProductLoading = MutableLiveData<Boolean>()
    private val deleteProductLoading: LiveData<Boolean> = _deleteProductLoading

    private val _createProductLoading = MutableLiveData<Boolean>()
    private val createProductLoading: LiveData<Boolean> = _createProductLoading

    private val _createShoppingListLoading = MutableLiveData<Boolean>()
    private val createShoppingListLoading: LiveData<Boolean> = _createShoppingListLoading

    private val shoppingListType: LiveData<ShoppingListType> = Transformations.map(currentScreen) {
        when (it) {
            is Screen.CurrentShoppingList -> ShoppingListType.CURRENT
            is Screen.CurrentProductList -> ShoppingListType.CURRENT
            is Screen.ArchivedShoppingList -> ShoppingListType.ARCHIVED
            is Screen.ArchivedProductList -> ShoppingListType.ARCHIVED
            else -> null
        }
    }

    private val shoppingLists = Transformations.switchMap(shoppingListType) {
        when (it) {
            ShoppingListType.CURRENT -> shoppingListRepository.getCurrentShoppingList()
            ShoppingListType.ARCHIVED -> shoppingListRepository.getArchivedShoppingList()
            else -> null
        }
    }

    private val selectedShoppingList = Transformations.map(currentScreen) {
        when (it) {
            is Screen.CurrentProductList -> it.shoppingList
            is Screen.ArchivedProductList -> it.shoppingList
            else -> null
        }
    }

    private val productList = Transformations.switchMap(selectedShoppingList) {
        if (it == null) {
            MutableLiveData<List<Product>>().apply { value = null }
        } else {
            shoppingListRepository.getAllShoppingListItem(it.id)
        }
    }

    private val shoppingListsUi = Transformations.map(shoppingLists) { it?.asUiModel() }
    private val isShoppingListsEmpty =
        Transformations.map(shoppingListsUi) { it?.isEmpty() ?: true }
    private val productListUi =
        Transformations.map(productList) { shoppingListType.value?.let { type -> it?.asUiModel(type) } }
    private val isProductListsEmpty = Transformations.map(productListUi) { it?.isEmpty() ?: true }

    fun updateShoppingList(shoppingList: ShoppingListUi, isArchived: Boolean) {
        _updateShoppingListLoading.value = true
        shoppingList.isArchived = isArchived
        viewModelScope.launch {
            shoppingListRepository.updateShoppingList(shoppingList.asDomainModel())
            _updateShoppingListLoading.value = false
        }
    }

    fun createShoppingList(name: String) {
        _createShoppingListLoading.value = true
        val shoppingList = ShoppingList(shoppingListName = name)

        viewModelScope.launch {
            shoppingListRepository.insertShoppingList(shoppingList)
            _createShoppingListLoading.value = false
        }
    }

    fun createProduct(name: String, quantity: Long, shoppingListId: Long) {
        _createProductLoading.value = true

        val product = Product(
            productName = name,
            productQuantity = quantity,
            shoppingListId = shoppingListId
        )
        viewModelScope.launch {
            shoppingListRepository.insertProduct(product)
            _createProductLoading.value = false
        }
    }

    fun deleteProduct(product: ProductUi) {
        _deleteProductLoading.value = true
        viewModelScope.launch {
            shoppingListRepository.deleteProduct(product.asDomainModel())
            _deleteProductLoading.value = false
        }
    }

    private val _screenState = MediatorLiveData<MutableScreenState>().apply {
        value = MutableScreenState(Screen.CurrentShoppingList)
        addSource(currentScreen) { value?.currentScreen = it; value = value }
        addSource(selectedShoppingList) { value?.selectedShoppingList = it; value = value }
        addSource(updateShoppingListLoading) {
            value?.updateShoppingListLoading = it; value = value
        }
        addSource(deleteProductLoading) { value?.deleteProductLoading = it; value = value }
        addSource(createProductLoading) { value?.createProductLoading = it; value = value }
        addSource(createShoppingListLoading) {
            value?.createShoppingListLoading = it; value = value
        }
        addSource(shoppingListsUi) { value?.shoppingListsUi = it }
        addSource(isShoppingListsEmpty) { value?.isShoppingListsEmpty = it; value = value }
        addSource(productListUi) { value?.productListUi = it }
        addSource(isProductListsEmpty) { value?.isProductListsEmpty = it; value = value }
    }
    val screenState: LiveData<ScreenState> = Transformations.map(_screenState) { it.copy() }
}