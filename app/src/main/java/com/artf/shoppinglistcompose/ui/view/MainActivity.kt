package com.artf.shoppinglistcompose.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.activity.compose.setContent
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.ScreenBackStack
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.view.layout.ShoppingListApp
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModel()
    private val backStack: ScreenBackStack by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                AmbientSharedViewModel provides sharedViewModel
            ) { ShoppingListApp() }
        }
    }

    override fun onBackPressed() {
        backStack.popBackStack() ?: super.onBackPressed()
    }
}
