package com.artf.shoppinglistcompose.ui.view.menu

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.LinearGradient
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredWidth
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.res.stringResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.px
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.data.state.ScreenState
import com.artf.shoppinglistcompose.ui.data.SharedViewModelAmbient
import com.artf.shoppinglistcompose.util.dpToPx

@Composable
fun AppDrawer(
    currentScreenState: ScreenState,
    closeDrawer: () -> Unit
) {
    val sharedViewModel = SharedViewModelAmbient.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = R.drawable.ic_baseline_home_24,
            label = stringResource(R.string.drawer_home),
            isSelected = currentScreenState == ScreenState.CurrentShoppingList,
            action = {
                closeDrawer()
                sharedViewModel.pushBackStack(ScreenState.CurrentShoppingList)
            }
        )
        DrawerButton(
            icon = R.drawable.ic_archive_black_24dp,
            label = stringResource(R.string.drawer_archived),
            isSelected = currentScreenState == ScreenState.ArchivedShoppingList,
            action = {
                closeDrawer()
                sharedViewModel.pushBackStack(ScreenState.ArchivedShoppingList)
            }
        )
    }
}

@Composable
private fun AppLogo(modifier: Modifier = Modifier) {
    Row {
        Surface(
            modifier = modifier.fillMaxWidth().drawBackground(
                brush = LinearGradient(
                    colors = listOf(MaterialTheme.colors.primary, Color.Red),
                    startX = 0.px,
                    startY = 0.px,
                    endX = (200.dpToPx()).px,
                    endY = (200.dpToPx()).px
                ),
                shape = RoundedCornerShape(8.dp)
            ),
            shape = RoundedCornerShape(8.dp),
            color = Color.Transparent
        ) {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .gravity(Alignment.CenterVertically),
                color = MaterialTheme.colors.onPrimary,
                style = TextStyle(
                    fontSize = TextUnit.Sp(20),
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
private fun DrawerButton(
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected) 1f else 0.6f
    val textIconColor = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.6f)
    val backgroundColor = if (isSelected) colors.primary.copy(alpha = 0.12f) else colors.surface

    val surfaceModifier = modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp).fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = RoundedCornerShape(4.dp)
    ) {
        TextButton(onClick = action, modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Image(
                    asset = vectorResource(icon),
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = imageAlpha
                )
                Spacer(Modifier.preferredWidth(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2.copy(color = textIconColor),
                    modifier = Modifier.fillMaxWidth().gravity(Alignment.CenterVertically)
                )
            }
        }
    }
}