package com.artf.shoppinglistcompose.ui.view

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.launchApp
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.MutableScreenUi
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.shoppingListNameState
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.ui.theme.util.TestTag.dialogAdd
import com.artf.shoppinglistcompose.ui.theme.util.TestTag.dialogCancel
import com.artf.shoppinglistcompose.ui.theme.util.TestTag.fab
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.Before
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
    val composeTestRule = createComposeRule()

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
        composeTestRule.onAllNodesWithText(appContext.getString(R.string.app_name))[0].assertIsDisplayed()
    }

    @Test
    fun openAndCloseDialog() {
        composeTestRule.onNodeWithTag(fab).performClick()
        composeTestRule.onNodeWithTag(dialogCancel).performClick()
        composeTestRule.onAllNodesWithText(dialogCancel)[0].assertDoesNotExist()
    }

    @Test
    fun addShoppingList() {
        val shoppingListName = "TestTask"
        composeTestRule.runOnIdle { shoppingListNameState.value = shoppingListName }
        composeTestRule.onNodeWithTag(fab).performClick()
        composeTestRule.onNodeWithTag(dialogAdd).performClick()
        verify(sharedViewModel).createShoppingList(shoppingListName)
    }
}