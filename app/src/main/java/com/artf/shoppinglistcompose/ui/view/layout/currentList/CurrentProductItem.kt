package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ProductUi

@Composable
fun ProductCurrentItem(
    sharedViewModel: SharedViewModel,
    product: ProductUi
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier.clickable(onClick = {

            })
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
                Text(
                    text = product.productName,
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                        .padding(8.dp)
                )
                Text(
                    text = product.productQuantity.toString(),
                    modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                        .padding(8.dp)
                )
                Column(horizontalAlignment = Alignment.End) {
                    IconButton(onClick = { sharedViewModel.deleteProduct(product) }) {
                        Icon(
                            ImageVector.vectorResource(R.drawable.ic_delete_forever_black_24dp),
                            "",
                            Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
    }
}