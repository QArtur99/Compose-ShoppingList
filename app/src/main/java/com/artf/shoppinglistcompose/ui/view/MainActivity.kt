package com.artf.shoppinglistcompose.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.ui.core.setContent
import com.artf.shoppinglistcompose.ui.data.*
import com.artf.shoppinglistcompose.ui.view.layout.ShoppingListApp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()
    private val backStack: ScreenBackStackImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(
                SharedViewModelAmbient provides sharedViewModel
            ) { ShoppingListApp() }
        }
    }

    override fun onBackPressed() {
        backStack.popBackStack() ?: super.onBackPressed()
    }
}
