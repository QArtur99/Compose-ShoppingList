package com.artf.shoppinglistcompose.ui.view.layout.archivedList

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.data.status.ResultStatus
import com.artf.shoppinglistcompose.ui.model.AmbientScreenState
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.model.model.compose.ArchivedShoppingListModel
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen
import com.artf.shoppinglistcompose.ui.view.menu.AppDrawer
import com.artf.shoppinglistcompose.ui.view.menu.MainMenu

@Composable
fun ShoppingListArchivedScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(ArchivedShoppingListModel.drawerState.value)
    )
) {
    val screenState = AmbientScreenState.current.currentScreenState
    ArchivedShoppingListModel.drawerState.value = scaffoldState.drawerState.value

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreenState = screenState,
                closeDrawer = {
                    scaffoldState.drawerState.close()
                    ArchivedShoppingListModel.drawerState.value = scaffoldState.drawerState.value
                }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState.open() }) {
                        Icon(vectorResource(R.drawable.ic_baseline_menu_24))
                    }
                },
                actions = { MainMenu() }
            )
        },
        bodyContent = { ScreenBody() }
    )
}

@Composable
private fun ScreenBody() {
    when (val result = AmbientScreenState.current.shoppingListsUi) {
        is ResultStatus.Loading -> LoadingScreen()
        is ResultStatus.Success -> SuccessScreen(result.data)
        is ResultStatus.Error -> ErrorScreen()
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}

@Composable
private fun SuccessScreen(productList: List<ShoppingListUi>) {
    val sharedViewModel = AmbientSharedViewModel.current
    ScrollableColumn {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            productList.forEach { post -> ShoppingListArchivedItem(sharedViewModel, post) }
        }
    }
}

@Composable
private fun ErrorScreen() {
    EmptyScreen(
        R.string.empty_view_shopping_list_title,
        R.string.empty_view_shopping_list_subtitle
    )
}