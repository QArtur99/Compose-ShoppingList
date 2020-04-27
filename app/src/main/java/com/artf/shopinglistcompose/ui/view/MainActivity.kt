package com.artf.shopinglistcompose.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.ui.core.setContent
import com.artf.shopinglistcompose.ui.data.*
import com.artf.shopinglistcompose.ui.view.layout.ShoppingListApp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()
    private val newListViewModel: NewListViewModel by viewModel()
    private val newProductViewModel: NewProductViewModel by viewModel()
    private val backStack: ScreenBackStack by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(
                ScreenBackStackAmbient provides backStack,
                SharedViewModelAmbient provides sharedViewModel,
                NewListViewModelAmbient provides newListViewModel,
                NewProductViewModelAmbient provides newProductViewModel
            ) { ShoppingListApp() }
        }
    }

    override fun onBackPressed() {
        backStack.pop() ?: super.onBackPressed()
    }
}
