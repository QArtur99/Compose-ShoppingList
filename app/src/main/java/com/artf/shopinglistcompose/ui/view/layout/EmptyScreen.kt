package com.artf.shopinglistcompose.ui.view.layout

import androidx.annotation.StringRes
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.RowScope.gravity
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.res.stringResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp

@Composable
fun EmptyScreen(
    @StringRes titleId: Int,
    @StringRes subTitleId: Int
) {
    Box(
        modifier = Modifier.fillMaxHeight(),
        gravity = ContentGravity.Center
    ) {
        Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 64.dp)) {
            Text(
                text = stringResource(titleId),
                modifier = Modifier.fillMaxWidth().gravity(Alignment.CenterVertically),
                color = MaterialTheme.colors.onSurface,
                style = TextStyle(
                    fontSize = TextUnit.Sp(18),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = stringResource(subTitleId),
                modifier = Modifier.fillMaxWidth().gravity(Alignment.CenterVertically),
                color = MaterialTheme.colors.onSurface,
                style = TextStyle(
                    fontSize = TextUnit.Sp(16),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}