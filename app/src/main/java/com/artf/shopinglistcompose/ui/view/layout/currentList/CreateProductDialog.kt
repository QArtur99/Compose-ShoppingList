package com.artf.shopinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.stringResource
import androidx.ui.text.TextRange
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.artf.data.database.model.ShoppingList
import com.artf.shopinglistcompose.R
import com.artf.shopinglistcompose.ui.view.MainActivity

@Composable
fun CreateProductDialog(
    showDialog: MutableState<Boolean>,
    shoppingList: ShoppingList
) {
    if (showDialog.value.not()) return
    val productName = state { "" }
    val productQuantity = state { "" }
    AlertDialog(
        onCloseRequest = { showDialog.value = false },
        title = {
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                text = stringResource(id = R.string.add_new_product)
            )
        },
        text = {
            Column {
                ProductEditText(productName)
                Spacer(Modifier.preferredHeight(16.dp))
                QuantityEditText(productQuantity)
            }
        },
        buttons = { DialogButtons(showDialog, productName, productQuantity, shoppingList) }
    )
}

@Composable
private fun DialogButtons(
    showDialog: MutableState<Boolean>,
    productName: MutableState<String>,
    productQuantity: MutableState<String>,
    shoppingList: ShoppingList
) {
    Box(Modifier.fillMaxWidth().padding(all = 8.dp), gravity = ContentGravity.CenterEnd) {
        Row(
            modifier = Modifier.weight(1f, true),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(Modifier.weight(0.3f, true))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = { showDialog.value = false },
                border = Border(2.dp, MaterialTheme.colors.primary),
                backgroundColor = MaterialTheme.colors.surface,
                text = {
                    Text(
                        text = "Cancel",
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            )
            Spacer(Modifier.preferredWidth(8.dp))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = {
                    if (productName.value.isEmpty().not() && productQuantity.value.isEmpty()
                            .not()
                    ) {
                        MainActivity.instance.newProductViewModel.createProduct(
                            productName.value,
                            productQuantity.value.toLong(),
                            shoppingList.id
                        )
                        showDialog.value = false
                    }
                },
                text = {
                    Text(
                        text = "Add",
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            )
        }
    }
}

@Composable
private fun ProductEditText(state: MutableState<String>) {
    val focus = state { true }
    val selection = state { TextRange(0, 0) }
    Column {
        Box {
            TextField(
                value = TextFieldValue(state.value, selection.value),
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Done,
                onFocus = { focus.value = true },
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = {
                    state.value = it.text
                    selection.value = TextRange(it.text.length, it.text.length)
                }
            )
            if (state.value.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.enter_product_name),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
        if (focus.value) {
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(start = 0.dp, end = 0.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun QuantityEditText(state: MutableState<String>) {
    val focus = state { true }
    val selection = state { TextRange(0, 0) }
    Column {
        Box {
            TextField(
                value = TextFieldValue(state.value, selection.value),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number,
                onFocus = { focus.value = true },
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = {
                    state.value = it.text
                    selection.value = TextRange(it.text.length, it.text.length)
                }
            )
            if (state.value.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.enter_quantity),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
        if (focus.value) {
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(start = 0.dp, end = 0.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}