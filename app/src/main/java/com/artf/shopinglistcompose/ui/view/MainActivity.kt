package com.artf.shopinglistcompose.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.ui.core.setContent
import com.artf.shopinglistcompose.ui.view.layout.ScreenAmbient
import com.artf.shopinglistcompose.ui.view.layout.ScreenBackStack
import com.artf.shopinglistcompose.ui.view.layout.ShoppingListApp
import com.artf.shopinglistcompose.ui.view.productDialog.NewProductViewModel
import com.artf.shopinglistcompose.ui.view.shoppingListDialog.NewListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val sharedViewModel: SharedViewModel by viewModel()
    val newListViewModel: NewListViewModel by viewModel()
    val newProductViewModel: NewProductViewModel by viewModel()
    private lateinit var backStack: ScreenBackStack

    companion object {
        lateinit var instance: MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContent {
            backStack = ScreenBackStack()
            Providers(ScreenAmbient provides backStack) {
                ShoppingListApp()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
//            R.id.current_shopping_list -> sharedViewModel.setShoppingListType(ShoppingListType.CURRENT)
//            R.id.archived_shopping_list -> sharedViewModel.setShoppingListType(ShoppingListType.ARCHIVED)
        }
        return true
    }

    override fun onBackPressed() {
        backStack.pop() ?: super.onBackPressed()
    }
}
