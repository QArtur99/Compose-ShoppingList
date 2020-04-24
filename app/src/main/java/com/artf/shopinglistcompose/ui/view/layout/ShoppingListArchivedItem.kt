package com.artf.shopinglistcompose.ui.view.layout

import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.IconButton
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.view.SharedViewModel
import com.artf.shopinglistcompose.util.getDateFormat

@Composable
fun ShoppingListArchivedItem(
    sharedViewModel: SharedViewModel,
    post: ShoppingList
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
            Clickable(
                modifier = Modifier.ripple(),
                onClick = { Log.e("CLICK", "PostCardSimple") }
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
                    Text(
                        text = post.shoppingListName,
                        modifier = Modifier.weight(1f).gravity(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Column(horizontalGravity = Alignment.End) {
                        IconButton(onClick = { sharedViewModel.updateShoppingList(post, false) }) {
                            Icon(
                                vectorResource(R.drawable.ic_unarchive_black_24dp),
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