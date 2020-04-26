package com.artf.shopinglistcompose.ui.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artf.data.database.model.ShoppingList
import com.artf.data.repository.ShoppingListRepository
import kotlinx.coroutines.launch

class NewListViewModel constructor(
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {

    private val _createShoppingListLoading = MutableLiveData<Boolean>()
    val createShoppingListLoading: LiveData<Boolean> = _createShoppingListLoading

    fun createShoppingList(name: String) {
        _createShoppingListLoading.value = true
        val shoppingList =
            ShoppingList(
                shoppingListName = name
            )

        viewModelScope.launch {
            shoppingListRepository.insertShoppingList(shoppingList)
            _createShoppingListLoading.value = false
        }
    }
}