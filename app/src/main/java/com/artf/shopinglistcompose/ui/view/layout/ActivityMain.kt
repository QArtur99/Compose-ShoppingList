package com.artf.shopinglistcompose.ui.view.layout

import android.util.Log
import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.animation.Crossfade
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.CornerSize
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shopinglistcompose.R
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.ui.view.MainActivity
import com.artf.shopinglistcompose.ui.view.SharedViewModel

@Composable
fun ShoppingListApp() {
    MaterialTheme(
        colors = lightThemeColors,
        typography = themeTypography
    ) {
        AppContent(MainActivity.instance.sharedViewModel)
    }
}

@Composable
private fun AppContent(sharedViewModel: SharedViewModel) {
    Crossfade(AppStatus.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.Home -> HomeScreen(sharedViewModel)
            }
        }
    }
}

@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val showDialog = state { false }
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Jetnews") },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(R.drawable.ic_launcher_background))
                    }
                }
            )
        },
        bodyContent = { modifier ->
            HomeScreenBody(sharedViewModel.shoppingLists, modifier)
            ShoppingListDialog(showDialog)
        },
        floatingActionButton = { Fab(showDialog) }
    )
}

@Composable
private fun HomeScreenBody(
    posts: LiveData<List<ShoppingList>>,
    modifier: Modifier
) {
    val posts2 = MutableLiveData<List<ShoppingList>>().apply {
        value = arrayListOf(
            ShoppingList(id = 0, shoppingListName = "A"),
            ShoppingList(id = 1, shoppingListName = "B"),
            ShoppingList(id = 2, shoppingListName = "C"),
            ShoppingList(id = 3, shoppingListName = "D"),
            ShoppingList(id = 4, shoppingListName = "E")
        )
    }

    val post3 = observer(posts)

    VerticalScroller {
        Column(modifier) {
            post3?.forEach { post ->
                PostCardSimple(post)
                HomeScreenDivider()
            }
        }
    }
}

@Composable
fun PostCardSimple(post: ShoppingList) {
    Clickable(
        modifier = Modifier.ripple(),
        onClick = { Log.e("CLICK", "PostCardSimple") }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = post.shoppingListName)
        }
    }
}

@Composable
private fun HomeScreenDivider() {
    Divider(
        modifier = Modifier.padding(start = 14.dp, end = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
fun Fab(showDialog: MutableState<Boolean>) {
    FloatingActionButton(
        onClick = { showDialog.value = true },
        modifier = Modifier,
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = contentColorFor(MaterialTheme.colors.onSecondary),
        elevation = 6.dp,
        icon = { Icon(vectorResource(R.drawable.ic_add_black_24dp)) }
    )
}

