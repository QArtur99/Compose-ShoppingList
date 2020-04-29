package com.artf.shoppinglistcompose.ui.data.model

import com.artf.data.status.ResultStatus
import com.artf.shoppinglistcompose.ui.data.status.ScreenStatus

data class MutableScreenState(
    override var currentScreenStatus: ScreenStatus,
    override var selectedShoppingList: ShoppingListUi? = null,
    override var updateShoppingListLoading: Boolean? = null,
    override var deleteProductLoading: Boolean? = null,
    override var createProductLoading: Boolean? = null,
    override var createShoppingListLoading: Boolean? = null,
    override var shoppingListsUi: ResultStatus<List<ShoppingListUi>>? = null,
    override var productListUi: ResultStatus<List<ProductUi>>? = null
) : ScreenState(currentScreenStatus)

abstract class ScreenState(
    open val currentScreenStatus: ScreenStatus,
    open val selectedShoppingList: ShoppingListUi? = null,
    open val updateShoppingListLoading: Boolean? = null,
    open val deleteProductLoading: Boolean? = null,
    open val createProductLoading: Boolean? = null,
    open val createShoppingListLoading: Boolean? = null,
    open val shoppingListsUi: ResultStatus<List<ShoppingListUi>>? = null,
    open val productListUi: ResultStatus<List<ProductUi>>? = null
)