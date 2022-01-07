package com.artf.shoppinglistcompose.ui.view.menu

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artf.shoppinglistcompose.R
import com.artf.shoppinglistcompose.ui.model.AmbientSharedViewModel
import com.artf.shoppinglistcompose.ui.model.state.ScreenState
import com.artf.shoppinglistcompose.util.avoidLagging
import com.artf.shoppinglistcompose.util.dpToPx

@Composable
fun AppDrawer(
    currentScreenState: ScreenState,
    closeDrawer: () -> Unit
) {
    val sharedViewModel = AmbientSharedViewModel.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppLogo(Modifier.padding(16.dp))
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        DrawerButton(
            icon = R.drawable.ic_baseline_home_24,
            label = stringResource(R.string.drawer_home),
            isSelected = currentScreenState == ScreenState.CurrentShoppingList,
            action = {
                closeDrawer()
                avoidLagging {
                    sharedViewModel.pushBackStack(ScreenState.CurrentShoppingList)
                }
            }
        )
        DrawerButton(
            icon = R.drawable.ic_archive_black_24dp,
            label = stringResource(R.string.drawer_archived),
            isSelected = currentScreenState == ScreenState.ArchivedShoppingList,
            action = {
                closeDrawer()
                avoidLagging {
                    sharedViewModel.pushBackStack(ScreenState.ArchivedShoppingList)
                }
            }
        )
    }
}

@Composable
private fun AppLogo(modifier: Modifier = Modifier) {
    Row {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(MaterialTheme.colors.primary, Color.Red),
                        start = Offset(0f, 0f),
                        end = Offset(200.dpToPx(), 200.dpToPx())
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
                    .align(Alignment.CenterVertically),
                color = MaterialTheme.colors.onPrimary,
                style = TextStyle(
                    fontSize = 20.sp,
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

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = RoundedCornerShape(4.dp)
    ) {
        TextButton(onClick = action, modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()) {
                Image(
                    imageVector = ImageVector.vectorResource(icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = imageAlpha
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2.copy(color = textIconColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}