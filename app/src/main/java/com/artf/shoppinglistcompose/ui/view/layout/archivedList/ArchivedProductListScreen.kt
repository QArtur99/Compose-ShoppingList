package com.artf.shoppinglistcompose.ui.view.layout.archivedList

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.ScreenStateAmbient
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.view.layout.EmptyScreen

@Composable
fun ArchivedProductListScreen(
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    val sharedViewModel = SharedViewModelAmbient.current
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = { sharedViewModel.popBackStack() }) {
                        Icon(vectorResource(R.drawable.ic_baseline_arrow_back_24))
                    }
                }
            )
        },
        bodyContent = { ScreenBody() }
    )
}

@Composable
private fun ScreenBody() {
    val productList = ScreenStateAmbient.current.productListUi
    val isEmpty = ScreenStateAmbient.current.isProductListsEmpty

    if (isEmpty == true) {
        EmptyScreen(
            R.string.empty_view_product_list_title,
            R.string.empty_view_product_list_subtitle_text
        )
        return
    }

    VerticalScroller {
        Column(Modifier.fillMaxWidth().padding(8.dp, 8.dp, 8.dp, 96.dp)) {
            productList?.forEach { post -> ArchivedProductItem(post) }
        }
    }
}