package com.artf.shopinglistcompose.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.setContent
import com.artf.shopinglistcompose.ui.view.layout.ShoppingListApp
import com.artf.shopinglistcompose.ui.view.productDialog.NewProductViewModel
import com.artf.shopinglistcompose.ui.view.shoppingListDialog.NewListViewModel
import com.artf.shopinglistcompose.util.ShoppingListType
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val sharedViewModel: SharedViewModel by viewModel()
    val newListViewModel: NewListViewModel by viewModel()
    val newProductViewModel: NewProductViewModel by viewModel()

    companion object {
        lateinit var instance: MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        sharedViewModel.setShoppingListType(ShoppingListType.CURRENT)
        setContent {
            ShoppingListApp()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
//            R.id.current_shopping_list -> sharedViewModel.setShoppingListType(ShoppingListType.CURRENT)
//            R.id.archived_shopping_list -> sharedViewModel.setShoppingListType(ShoppingListType.ARCHIVED)
        }
        return true
    }
}
