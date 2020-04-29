package com.artf.shoppinglistcompose.ui.data.model

import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus

data class MutableScreenState(
    override var currentScreenStatus: ScreenStatus,
    override var selectedShoppingList: ShoppingListUi? = null,
    override var updateShoppingListLoading: Boolean? = null,
    override var deleteProductLoading: Boolean? = null,
    override var createProductLoading: Boolean? = null,
    override var createShoppingListLoading: Boolean? = null,
    override var shoppingListsUi: List<ShoppingListUi>? = null,
    override var isShoppingListsEmpty: Boolean? = null,
    override var productListUi: List<ProductUi>? = null,
    override var isProductListsEmpty: Boolean? = null
) : ScreenState(currentScreenStatus)

abstract class ScreenState(
    open val currentScreenStatus: ScreenStatus,
    open val selectedShoppingList: ShoppingListUi? = null,
    open val updateShoppingListLoading: Boolean? = null,
    open val deleteProductLoading: Boolean? = null,
    open val createProductLoading: Boolean? = null,
    open val createShoppingListLoading: Boolean? = null,
    open val shoppingListsUi: List<ShoppingListUi>? = null,
    open val isShoppingListsEmpty: Boolean? = null,
    open val productListUi: List<ProductUi>? = null,
    open val isProductListsEmpty: Boolean? = null
)