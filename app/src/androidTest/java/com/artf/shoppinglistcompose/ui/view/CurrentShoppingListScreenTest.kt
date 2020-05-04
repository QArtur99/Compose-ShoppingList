package com.artf.shoppinglistcompose.ui.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.ui.test.assertIsDisplayed
import androidx.ui.test.createComposeRule
import androidx.ui.test.doClick
import androidx.ui.test.findAllByTag
import androidx.ui.test.findByTag
import androidx.ui.test.runOnIdleCompose
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.findAllBySubstring
import com.artf.shoppinglistcompose.launchApp
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.MutableScreenUi
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.shoppingListNameState
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.ui.view.value.TestTag.dialogAdd
import com.artf.shoppinglistcompose.ui.view.value.TestTag.dialogCancel
import com.artf.shoppinglistcompose.ui.view.value.TestTag.fab
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CurrentShoppingListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule(disableTransitions = true)

    private lateinit var appContext: Context
    private val sharedViewModel = Mockito.mock(SharedViewModel::class.java)

    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        val screenUi = MutableScreenUi(ScreenState.CurrentShoppingList).apply { }
        `when`(sharedViewModel.screenState).thenReturn(MutableLiveData(screenUi))
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.launchApp { sharedViewModel }
    }

    @Test
    fun checkTitleOnActionBar() {
        findAllBySubstring(appContext.getString(R.string.app_name)).first().assertIsDisplayed()
    }

    @Ignore("Ignoring because of https://issuetracker.google.com/154617105")
    @Test
    fun openAndCloseDialog() {
        findByTag(fab).doClick()
        findByTag(dialogCancel).doClick()
        assertThat(findAllByTag(dialogCancel).size, `is`(0))
    }

    @Ignore("Ignoring because of https://issuetracker.google.com/154617105")
    @Test
    fun addShoppingList() {
        val shoppingListName = "TestTask"
        runOnIdleCompose { shoppingListNameState = shoppingListName }
        findByTag(fab).doClick()
        findByTag(dialogAdd).doClick()
        verify(sharedViewModel).createShoppingList(shoppingListName)
    }
}