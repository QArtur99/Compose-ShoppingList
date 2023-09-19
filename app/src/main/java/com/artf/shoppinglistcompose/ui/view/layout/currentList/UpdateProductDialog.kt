package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ProductUi
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel

@Composable
fun UpdateProductDialog(padding: PaddingValues, product: ProductUi? = null) {
    val sharedViewModelAmbient = AmbientSharedViewModel.current
    if (CurrentProductListModel.showUpdateDialogState.value.not()) return
    if (product == null) return

    CurrentProductListModel.productNameState.value = product.productName
    CurrentProductListModel.editTextProductNameSelectionState.value = TextRange(
        product.productName.length,
        product.productName.length,
    )

    CurrentProductListModel.productQuantityState.value = product.productQuantity.toString()
    CurrentProductListModel.editTextProductQuantitySelectionState.value = TextRange(
        product.productQuantity.toString().length,
        product.productQuantity.toString().length,
    )

    AlertDialog(
        onDismissRequest = { CurrentProductListModel.showDialogState.value = false },
        title = {
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                text = stringResource(id = R.string.dialog_title_edit_product),
            )
        },
        text = {
            Column {
                Spacer(Modifier.height(16.dp))
                UpdateProductEditText()
                Spacer(Modifier.height(16.dp))
                UpdateQuantityEditText()
            }
        },
        buttons = { UpdateDialogButtons(sharedViewModelAmbient, product) },
    )
}

@Composable
private fun UpdateDialogButtons(
    sharedViewModel: SharedViewModel,
    product: ProductUi,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
        ) {
            Spacer(Modifier.weight(0.3f, true))
            TextButton(
                modifier = Modifier.weight(0.35f, true),
                onClick = {
                    CurrentProductListModel.productNameState.value = ""
                    CurrentProductListModel.productQuantityState.value = ""
                    CurrentProductListModel.showUpdateDialogState.value = false
                },
                border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                colors = ButtonDefaults.outlinedButtonColors(),
                content = {
                    Text(
                        text = stringResource(R.string.dialog_button_cancel),
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp),
                    )
                },
            )
            Spacer(Modifier.width(8.dp))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = {
                    if (CurrentProductListModel.productNameState.value.isEmpty()
                            .not() && CurrentProductListModel.productQuantityState.value.isEmpty().not()
                    ) {
                        val newProduct = product.copy(
                            productName = CurrentProductListModel.productNameState.value,
                            productQuantity = CurrentProductListModel.productQuantityState.value.toLong()
                        )
                        sharedViewModel.updateProduct(newProduct)
                        CurrentProductListModel.showUpdateDialogState.value = false
                        CurrentProductListModel.productNameState.value = ""
                        CurrentProductListModel.productQuantityState.value = ""
                    }
                },
                content = {
                    Text(
                        text = stringResource(R.string.dialog_button_update),
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp),
                    )
                },
            )
        }
    }
}

@Composable
private fun UpdateProductEditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(
                    CurrentProductListModel.productNameState.value,
                    CurrentProductListModel.editTextProductNameSelectionState.value,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        CurrentProductListModel.editTextProductNameFocusState.value = true
                    },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { it ->
                    CurrentProductListModel.productNameState.value = it.text
                    CurrentProductListModel.editTextProductNameSelectionState.value =
                        TextRange(it.text.length, it.text.length)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.dialog_enter_product_name),
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp),
                    )
                },
            )
        }
    }
}

@Composable
private fun UpdateQuantityEditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(
                    CurrentProductListModel.productQuantityState.value,
                    CurrentProductListModel.editTextProductQuantitySelectionState.value,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        CurrentProductListModel.editTextProductQuantityFocusState.value = true
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { it ->
                    CurrentProductListModel.productQuantityState.value = it.text
                    CurrentProductListModel.editTextProductQuantitySelectionState.value =
                        TextRange(it.text.length, it.text.length)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.dialog_enter_quantity),
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp),
                    )
                },
            )
        }
    }
}
