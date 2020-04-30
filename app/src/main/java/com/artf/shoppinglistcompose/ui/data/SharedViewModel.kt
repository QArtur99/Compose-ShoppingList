package com.artf.shoppinglistcompose.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList
import com.artf.data.repository.ShoppingListRepository
import com.artf.data.status.ResultStatus
import com.artf.shoppinglistcompose.ui.data.mapper.asDomainModel
import com.artf.shoppinglistcompose.ui.data.mapper.asUiModel
import com.artf.shoppinglistcompose.ui.data.model.MutableScreenUi
import com.artf.shoppinglistcompose.ui.data.model.ProductUi
import com.artf.shoppinglistcompose.ui.data.model.ScreenUi
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import com.artf.shoppinglistcompose.ui.data.state.ShoppingListState
import com.artf.shoppinglistcompose.util.ext.addSourceInvoke
import com.artf.shoppinglistcompose.util.ext.mapNonNull
import kotlinx.coroutines.launch

class SharedViewModel constructor(
    private val backStack: ScreenBackStackImpl,
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel(), ScreenBackStack by backStack {

    private val _currentScreen = backStack.getCurrentScreen()
    private val currentScreenState: LiveData<ScreenState> = _currentScreen

    private val _updateShoppingListLoading = MutableLiveData<Boolean>()
    private val updateShoppingListLoading: LiveData<Boolean> = _updateShoppingListLoading

    private val _deleteProductLoading = MutableLiveData<Boolean>()
    private val deleteProductLoading: LiveData<Boolean> = _deleteProductLoading

    private val _createProductLoading = MutableLiveData<Boolean>()
    private val createProductLoading: LiveData<Boolean> = _createProductLoading

    private val _createShoppingListLoading = MutableLiveData<Boolean>()
    private val createShoppingListLoading: LiveData<Boolean> = _createShoppingListLoading

    private val shoppingListStatus = mapNonNull(currentScreenState) {
        when (it) {
            is ScreenState.CurrentShoppingList -> ShoppingListState.CURRENT
            is ScreenState.ArchivedShoppingList -> ShoppingListState.ARCHIVED
            else -> null
        }
    }

    private val shoppingLists = shoppingListStatus.switchMap {
        when (it) {
            ShoppingListState.CURRENT -> shoppingListRepository.getCurrentShoppingList()
            ShoppingListState.ARCHIVED -> shoppingListRepository.getArchivedShoppingList()
        }
    }

    private val shoppingListsUi = mapNonNull(shoppingLists) {
        when (it) {
            is ResultStatus.Loading -> ResultStatus.Loading
            is ResultStatus.Success -> ResultStatus.Success(it.data.asUiModel())
            is ResultStatus.Error -> it
        }
    }

    private val selectedShoppingList = mapNonNull(currentScreenState) {
        when (it) {
            is ScreenState.CurrentProductList -> it.shoppingList
            is ScreenState.ArchivedProductList -> it.shoppingList
            else -> null
        }
    }

    private val productList = selectedShoppingList.switchMap {
        shoppingListRepository.getProductList(it.id)
    }

    private val productListUi = mapNonNull(productList) {
        when (it) {
            is ResultStatus.Loading -> ResultStatus.Loading
            is ResultStatus.Success -> ResultStatus.Success(it.data.asUiModel())
            is ResultStatus.Error -> it
        }
    }

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

    private val _screenState = MediatorLiveData<MutableScreenUi>().apply {
        value = MutableScreenUi(ScreenState.CurrentShoppingList)
        addSource(currentScreenState) { value?.currentScreenState = it!! }
        addSource(selectedShoppingList) { value?.selectedShoppingList = it }
        addSourceInvoke(createShoppingListLoading) { value?.createShoppingListLoading = it }
        addSourceInvoke(updateShoppingListLoading) { value?.updateShoppingListLoading = it }
        addSourceInvoke(createProductLoading) { value?.createProductLoading = it }
        addSourceInvoke(deleteProductLoading) { value?.deleteProductLoading = it }
        addSourceInvoke(shoppingListsUi) { value?.shoppingListsUi = it }
        addSourceInvoke(productListUi) { value?.productListUi = it }
    }
    val screenState: LiveData<ScreenUi> = Transformations.map(_screenState) { it.copy() }
}
