package com.artf.shopinglistcompose.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artf.data.database.model.Product
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.ui.data.model.ProductUi
import com.artf.data.repository.ShoppingListRepository
import com.artf.shopinglistcompose.util.ShoppingListType
import kotlinx.coroutines.launch

class SharedViewModel constructor(
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {

    private val _shoppingListType = MutableLiveData<ShoppingListType>()
    val shoppingListType: LiveData<ShoppingListType> = _shoppingListType

    private val _selectedShoppingList = MutableLiveData<ShoppingList>()
    val selectedShoppingList: LiveData<ShoppingList> = _selectedShoppingList

    private val _createItem = MutableLiveData<Boolean>()
    val createItem: LiveData<Boolean> = _createItem

    private val _updateShoppingListLoading = MutableLiveData<Boolean>()
    val updateShoppingListLoading: LiveData<Boolean> = _updateShoppingListLoading

    private val _deleteProductLoading = MutableLiveData<Boolean>()
    val deleteProductLoading: LiveData<Boolean> = _deleteProductLoading

    private val _createProductLoading = MutableLiveData<Boolean>()
    val createProductLoading: LiveData<Boolean> = _createProductLoading

    private val _createShoppingListLoading = MutableLiveData<Boolean>()
    val createShoppingListLoading: LiveData<Boolean> = _createShoppingListLoading

    val shoppingLists = Transformations.switchMap(shoppingListType) {
        when (it) {
            ShoppingListType.CURRENT -> shoppingListRepository.getCurrentShoppingList()
            ShoppingListType.ARCHIVED -> shoppingListRepository.getArchivedShoppingList()
        }
    }

    val productList = Transformations.switchMap(_selectedShoppingList) {
        if (it == null) {
            MutableLiveData<List<Product>>().apply { value = null }
        } else {
            shoppingListRepository.getAllShoppingListItem(it.id)
        }
    }

    val productListUi = Transformations.map(productList) { it?.asUiModel(shoppingListType.value!!) }
    val isShoppingListsEmpty = Transformations.map(shoppingLists) { it?.isEmpty() ?: false }
    val isProductListsEmpty = Transformations.map(productListUi) { it?.isEmpty() ?: false }

    fun setShoppingListType(shoppingListType: ShoppingListType) {
        if (_shoppingListType.value != shoppingListType)
            _shoppingListType.value = shoppingListType
    }

    fun onShoppingListClick(shoppingList: ShoppingList?) {
        _selectedShoppingList.value = shoppingList
    }

    fun onFabClicked(show: Boolean?) {
        _createItem.value = show
    }

    fun updateShoppingList(shoppingList: ShoppingList, isArchived: Boolean) {
        _updateShoppingListLoading.value = true
        shoppingList.isArchived = isArchived
        viewModelScope.launch {
            shoppingListRepository.updateShoppingList(shoppingList)
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
            shoppingListRepository.deleteProduct(product.asDbModel())
            _deleteProductLoading.value = false
        }
    }
}
