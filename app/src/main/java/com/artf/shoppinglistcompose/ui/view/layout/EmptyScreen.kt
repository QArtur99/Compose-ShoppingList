package com.artf.shoppinglistcompose.ui.view.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyScreen(
    @StringRes titleId: Int,
    @StringRes subTitleId: Int
) {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 64.dp)) {
            Text(
                text = stringResource(titleId),
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = stringResource(subTitleId),
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.onSurface,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}