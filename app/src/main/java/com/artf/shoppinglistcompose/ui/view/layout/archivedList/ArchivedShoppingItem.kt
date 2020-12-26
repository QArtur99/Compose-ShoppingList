package com.artf.shoppinglistcompose.ui.view.layout.archivedList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.util.getDateFormat

@Composable
fun ShoppingListArchivedItem(
    sharedViewModel: SharedViewModel,
    post: ShoppingListUi
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.clickable(onClick = {
                sharedViewModel.pushBackStack(ScreenState.ArchivedProductList(post))
            })
        ) {

            Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
                Text(
                    text = post.shoppingListName,
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                        .padding(8.dp)
                )
                Column(horizontalAlignment = Alignment.End) {
                    IconButton(onClick = { sharedViewModel.updateShoppingList(post, false) }) {
                        Icon(
                            vectorResource(R.drawable.ic_unarchive_black_24dp),
                            Modifier.fillMaxSize()
                        )
                    }
                    Text(
                        text = post.shoppingListTimestamp.getDateFormat(),
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.padding(end = 8.dp),
                        style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic)
                    )
                }
            }
        }
    }
}