package com.artf.shopinglistcompose.ui.view.layout

import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.LifecycleOwnerAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.IconButton
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.view.SharedViewModel
import com.artf.shopinglistcompose.util.getDateFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun PostCardSimple(
    sharedViewModel: SharedViewModel,
    post: ShoppingList
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
            Clickable(
                modifier = Modifier.ripple(),
                onClick = { Log.e("CLICK", "PostCardSimple") }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(all = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f).gravity(Alignment.CenterVertically)
                            .padding(8.dp),
                        text = post.shoppingListName
                    )
                    Column(horizontalGravity = Alignment.End) {
                        IconButton(onClick = { sharedViewModel.updateShoppingList(post, true) }) {
                            Icon(
                                vectorResource(R.drawable.ic_archive_black_24dp),
                                Modifier.fillMaxSize()
                            )
                        }
                        Text(text = post.shoppingListTimestamp.getDateFormat())
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPostCardSimple() {
    val sharedViewModel = LifecycleOwnerAmbient.current.viewModel<SharedViewModel>()
    val shoppingList = ShoppingList(id = 0, shoppingListName = "A")
    PostCardSimple(sharedViewModel.value, shoppingList)
}
