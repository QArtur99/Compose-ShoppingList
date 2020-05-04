package com.artf.shoppinglistcompose.ui.model.model

import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.ui.model.state.ScreenState

data class MutableScreenUi(
    override var currentScreenState: ScreenState,
    override var selectedShoppingList: ShoppingListUi? = null,
    override var updateShoppingListLoading: Boolean? = null,
    override var deleteProductLoading: Boolean? = null,
    override var createProductLoading: Boolean? = null,
    override var createShoppingListLoading: Boolean? = null,
    override var shoppingListsUi: ResultStatus<List<ShoppingListUi>>? = null,
    override var productListUi: ResultStatus<List<ProductUi>>? = null
) : ScreenUi(currentScreenState)

abstract class ScreenUi(
    open val currentScreenState: ScreenState,
    open val selectedShoppingList: ShoppingListUi? = null,
    open val updateShoppingListLoading: Boolean? = null,
    open val deleteProductLoading: Boolean? = null,
    open val createProductLoading: Boolean? = null,
    open val createShoppingListLoading: Boolean? = null,
    open val shoppingListsUi: ResultStatus<List<ShoppingListUi>>? = null,
    open val productListUi: ResultStatus<List<ProductUi>>? = null
)