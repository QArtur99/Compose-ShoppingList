package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import com.artf.shoppinglistcompose.ui.model.AmbientScreenState
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.ShoppingListUi
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.editTextProductNameFocusState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.editTextProductNameSelectionState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.editTextProductQuantityFocusState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.editTextProductQuantitySelectionState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.productNameState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.productQuantityState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentProductListModel.showDialogState

@Composable
fun CreateProductDialog(padding : PaddingValues) {
    val sharedViewModelAmbient = AmbientSharedViewModel.current
    val selectedShoppingList = AmbientScreenState.current.selectedShoppingList ?: return
    if (showDialogState.value.not()) return
    AlertDialog(
        onDismissRequest = { showDialogState.value = false },
        title = {
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                text = stringResource(id = R.string.dialog_title_add_new_product)
            )
        },
        text = {
            Column {
                Spacer(Modifier.height(16.dp))
                ProductEditText()
                Spacer(Modifier.height(16.dp))
                QuantityEditText()
            }
        },
        buttons = { DialogButtons(sharedViewModelAmbient, selectedShoppingList) }
    )
}

@Composable
private fun DialogButtons(
    sharedViewModel: SharedViewModel,
    shoppingList: ShoppingListUi
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp), contentAlignment = Alignment.CenterEnd) {
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(Modifier.weight(0.3f, true))
            TextButton(
                modifier = Modifier.weight(0.35f, true),
                onClick = { showDialogState.value = false },
                border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                colors = ButtonDefaults.outlinedButtonColors(),
                content = {
                    Text(
                        text = stringResource(R.string.dialog_button_cancel),
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            )
            Spacer(Modifier.width(8.dp))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = {
                    if (productNameState.value.isEmpty()
                            .not() && productQuantityState.value.isEmpty().not()
                    ) {
                        sharedViewModel.createProduct(
                            productNameState.value,
                            productQuantityState.value.toLong(),
                            shoppingList.id
                        )
                        showDialogState.value = false
                        productNameState.value = ""
                        productQuantityState.value = ""
                    }
                },
                content = {
                    Text(
                        text = stringResource(R.string.dialog_button_add),
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            )
        }
    }
}

@Composable
private fun ProductEditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(
                    productNameState.value,
                    editTextProductNameSelectionState.value
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { editTextProductNameFocusState.value = true },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { it ->
                    productNameState.value = it.text
                    editTextProductNameSelectionState.value =
                        TextRange(it.text.length, it.text.length)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor =  MaterialTheme.colors.surface
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.dialog_enter_product_name),
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            )
        }
    }
}

@Composable
private fun QuantityEditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(
                    productQuantityState.value,
                    editTextProductQuantitySelectionState.value
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { editTextProductQuantityFocusState.value = true },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { it ->
                    productQuantityState.value = it.text
                    editTextProductQuantitySelectionState.value =
                        TextRange(it.text.length, it.text.length)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor =  MaterialTheme.colors.surface
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.dialog_enter_quantity),
                        color = Color.Gray,
                        style = TextStyle(fontSize = 18.sp)
                    )
                }
            )
        }
    }
}