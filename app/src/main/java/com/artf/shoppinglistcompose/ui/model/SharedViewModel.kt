package com.artf.shoppinglistcompose.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.artf.shoppinglistcompose.data.database.model.Product
import com.artf.shoppinglistcompose.data.database.model.ShoppingList
import com.artf.shoppinglistcompose.data.repository.ShoppingListRepository
import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.testing.OpenForTesting
import com.artf.shoppinglistcompose.ui.model.mapper.asDomainModel
import com.artf.shoppinglistcompose.ui.model.mapper.asUiModel
import com.artf.shoppinglistcompose.ui.model.model.MutableScreenUi
import com.artf.shoppinglistcompose.ui.model.model.ProductUi
import com.artf.shoppinglistcompose.ui.model.model.ScreenUi
import com.artf.shoppinglistcompose.ui.model.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.ui.model.state.ShoppingListState
import com.artf.shoppinglistcompose.util.ext.addSourceInvoke
import com.artf.shoppinglistcompose.util.ext.mapNonNull
import kotlinx.coroutines.launch

@OpenForTesting
class SharedViewModel constructor(
    private val backStack: ScreenBackStack,
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel(), ScreenBackStack by backStack {

    private val currentScreenState = backStack.getCurrentScreen()

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

    fun createShoppingList(name: String) {
        _createShoppingListLoading.value = true
        val shoppingList = ShoppingList(shoppingListName = name)

        viewModelScope.launch {
            shoppingListRepository.insertShoppingList(shoppingList)
            _createShoppingListLoading.value = false
        }
    }

    fun updateShoppingList(shoppingList: ShoppingListUi, isArchived: Boolean) {
        _updateShoppingListLoading.value = true
        val shoppingListDomain = shoppingList.copy(isArchived = isArchived).asDomainModel()
        viewModelScope.launch {
            shoppingListRepository.updateShoppingList(shoppingListDomain)
            _updateShoppingListLoading.value = false
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
        addSource(currentScreenState) { value?.currentScreenState = it ?: return@addSource }
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
