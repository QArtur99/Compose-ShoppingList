package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.ui.core.Alignment
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
import androidx.ui.unit.dp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.model.ProductUi
import com.artf.shoppinglistcompose.ui.model.SharedViewModel

@Composable
fun ProductCurrentItem(
    sharedViewModel: SharedViewModel,
    product: ProductUi
) {
    Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
        Surface(shape = RoundedCornerShape(8.dp), elevation = 4.dp) {
            Clickable(
                modifier = Modifier.ripple(),
                onClick = { }
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(all = 8.dp)) {
                    Text(
                        text = product.productName,
                        modifier = Modifier.weight(1f).gravity(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Text(
                        text = product.productQuantity.toString(),
                        modifier = Modifier.weight(1f).gravity(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Column(horizontalGravity = Alignment.End) {
                        IconButton(onClick = { sharedViewModel.deleteProduct(product) }) {
                            Icon(
                                vectorResource(R.drawable.ic_delete_forever_black_24dp),
                                Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}