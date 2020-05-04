package com.artf.shoppinglistcompose.ui.view.layout.currentList

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.TestTag
import androidx.ui.foundation.Border
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.TextFieldValue
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.RowScope.weight
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredWidth
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
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.SharedViewModel
import com.artf.shoppinglistcompose.ui.model.SharedViewModelAmbient
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.editTextFocusState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.editTextSelectionState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.shoppingListNameState
import com.artf.shoppinglistcompose.ui.model.model.compose.CurrentShoppingListModel.showDialogState
import com.artf.shoppinglistcompose.ui.view.value.TestTag.dialogAdd
import com.artf.shoppinglistcompose.ui.view.value.TestTag.dialogCancel

@Composable
fun CreateShoppingListDialog() {
    val sharedViewModel = SharedViewModelAmbient.current
    if (showDialogState.not()) return

    AlertDialog(
        onCloseRequest = { showDialogState = false },
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
    Box(Modifier.fillMaxWidth().padding(all = 8.dp), gravity = ContentGravity.CenterEnd) {
        Row(
            modifier = Modifier.weight(1f, true),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(Modifier.weight(0.3f, true))
            TestTag(tag = dialogCancel) {
                Button(
                    modifier = Modifier.weight(0.35f, true),
                    onClick = { showDialogState = false },
                    border = Border(2.dp, MaterialTheme.colors.primary),
                    backgroundColor = MaterialTheme.colors.surface,
                    text = {
                        Text(
                            text = stringResource(R.string.dialog_button_cancel),
                            maxLines = 1,
                            style = TextStyle(fontSize = 14.sp)
                        )
                    }
                )
            }
            Spacer(Modifier.preferredWidth(8.dp))
            TestTag(tag = dialogAdd) {
                Button(
                    modifier = Modifier.weight(0.35f, true),
                    onClick = {
                        if (shoppingListNameState.isEmpty().not()) {
                            sharedViewModel.createShoppingList(shoppingListNameState)
                            showDialogState = false
                            shoppingListNameState = ""
                        }
                    },
                    text = {
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
}

@Composable
private fun EditText() {
    Column {
        Box {
            TextField(
                value = TextFieldValue(shoppingListNameState, editTextSelectionState),
                modifier = Modifier.fillMaxWidth(),
                imeAction = ImeAction.Done,
                onFocusChange = { editTextFocusState = true },
                textStyle = TextStyle(fontSize = 18.sp),
                onValueChange = {
                    shoppingListNameState = it.text
                    editTextSelectionState = TextRange(it.text.length, it.text.length)
                }
            )
            if (shoppingListNameState.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.dialog_enter_shopping_list_name),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
        if (editTextFocusState) {
            Divider(
                thickness = 2.dp,
                modifier = Modifier.padding(start = 0.dp, end = 0.dp),
                color = MaterialTheme.colors.secondary
            )
        }
    }
}