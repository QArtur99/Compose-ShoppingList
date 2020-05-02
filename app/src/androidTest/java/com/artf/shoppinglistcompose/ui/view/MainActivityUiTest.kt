package com.artf.shoppinglistcompose.ui.view

import android.content.Context
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.ui.test.assertIsDisplayed
import androidx.ui.test.createComposeRule
import androidx.ui.test.doClick
import androidx.ui.test.findAllByTag
import com.artf.data.repository.ShoppingListRepository
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.findAllBySubstring
import com.artf.shoppinglistcompose.launchApp
import com.artf.shoppinglistcompose.ui.data.ScreenBackStackImpl
import com.artf.shoppinglistcompose.ui.data.SharedViewModel
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@MediumTest
@RunWith(JUnit4::class)
class MainActivityUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var appContext: Context

    private val shoppingListRepository = Mockito.mock(ShoppingListRepository::class.java)
    lateinit var screenBackStackImpl: ScreenBackStackImpl
    lateinit var sharedViewModel: SharedViewModel

    @Before
    fun setUp() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.launchApp {
            screenBackStackImpl = ScreenBackStackImpl()
            sharedViewModel = SharedViewModel(screenBackStackImpl, shoppingListRepository)
            sharedViewModel
        }
    }

    @Test
    fun checkTitleOnActionBar() {
        findAllBySubstring(appContext.getString(R.string.app_name)).first().assertIsDisplayed()
    }

    @Ignore("tag doesn't work")
    @Test
    fun opensArticle() {
        findAllByTag("Fab").first().doClick()
    }
}