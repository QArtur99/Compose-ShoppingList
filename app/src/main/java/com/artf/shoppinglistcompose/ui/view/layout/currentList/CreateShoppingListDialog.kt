package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.editTextFocusState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.editTextSelectionState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.shoppingListNameState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.showDialogState

@Composable
fun CreateShoppingListDialog() {
    val sharedViewModel = AmbientSharedViewModel.current
    if (showDialogState.value.not()) return

    AlertDialog(
        onDismissRequest = { showDialogState.value = false },
        title = {
            Text(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp),
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                text = stringResource(id = R.string.dialog_title_add_new_shopping_list)
            )
        },
        text = { EditText() },
        buttons = { DialogButtons(sharedViewModel) }
    )
}

@Composable
private fun DialogButtons(sharedViewModel: SharedViewModel) {
    Box(Modifier.fillMaxWidth().padding(all = 8.dp), contentAlignment = Alignment.CenterEnd) {
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(Modifier.weight(0.3f, true))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = { showDialogState.value = false },
                border = BorderStroke(2.dp, MaterialTheme.colors.primary),
                content = {
                    Text(
                        text = stringResource(R.string.dialog_button_cancel),
                        maxLines = 1,
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            )
            Spacer(Modifier.preferredWidth(8.dp))
            Button(
                modifier = Modifier.weight(0.35f, true),
                onClick = {
                    if (shoppingListNameState.value.isEmpty().not()) {
                        sharedViewModel.createShoppingList(shoppingListNameState.value)
                        showDialogState.value = false
                        shoppingListNameState.value = ""
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
private fun EditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(shoppingListNameState.value, editTextSelectionState.value),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { editTextFocusState.value = true },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = { it ->
                    shoppingListNameState.value = it.text
                    editTextSelectionState.value = TextRange(it.text.length, it.text.length)
                }
            )
            if (shoppingListNameState.value.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.dialog_enter_shopping_list_name),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
        if (editTextFocusState.value) {
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(start = 0.dp, end = 0.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}