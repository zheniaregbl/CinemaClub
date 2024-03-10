package ru.syndicate.cinemaclub.ui.bottom_nav_bar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import cafe.adriel.voyager.navigator.Navigator
import ru.syndicate.cinemaclub.data.model.BottomNavBarItem
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.utils.CustomScreen

@Composable
fun BottomNavBar(
    modifier: Modifier,
    navigator: Navigator
) {

    val items = listOf(
        BottomNavBarItem.Home,
        BottomNavBarItem.Cinema,
        BottomNavBarItem.Ticket,
        BottomNavBarItem.Profile
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        for (index in items.indices) {

            NavBarItem(
                icon = items[index].icon,
                isSelected = navigator.lastItem.key == items[index].screen.key,
                onClick = {
                    navigator.replace(items[index].screen)
                }
            )
        }
    }
}

@Composable
fun NavBarItem(
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Icon(
        modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onClick() },
        imageVector = ImageVector.vectorResource(id = icon),
        contentDescription = null,
        tint = if (isSelected) Color.White else CustomGray
    )
}