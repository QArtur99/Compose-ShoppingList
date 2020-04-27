package com.artf.shoppinglistcompose.ui.view.layout.archivedList

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.IconButton
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.Screen
import com.artf.shoppinglistcompose.ui.data.ScreenBackStackAmbient
import com.artf.shoppinglistcompose.ui.data.SharedViewModel
import com.artf.shoppinglistcompose.ui.data.model.ShoppingListUi
import com.artf.shoppinglistcompose.util.getDateFormat

@Composable
fun ShoppingListArchivedItem(
    sharedViewModel: SharedViewModel,
    post: ShoppingListUi
) {
    val backStack = ScreenBackStackAmbient.current
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
            Clickable(
                modifier = Modifier.ripple(),
                onClick = { backStack.push(Screen.ProductListArchived(post)) }
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