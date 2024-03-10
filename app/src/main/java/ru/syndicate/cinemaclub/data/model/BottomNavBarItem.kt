package ru.syndicate.cinemaclub.data.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.home_screen.HomeScreen
import ru.syndicate.cinemaclub.ui.screen.cinema_screen.CinemaScreen
import ru.syndicate.cinemaclub.ui.screen.profile_screen.ProfileScreen
import ru.syndicate.cinemaclub.ui.screen.ticket_screen.TicketScreen

sealed class BottomNavBarItem(
    val screen: Screen,
    val icon: Int
) {

    data object Home: BottomNavBarItem(
        screen = HomeScreen(),
        icon = R.drawable.svg_home
    )

    data object Cinema: BottomNavBarItem(
        screen = CinemaScreen(),
        icon = R.drawable.svg_map
    )

    data object Ticket: BottomNavBarItem(
        screen = TicketScreen(),
        icon = R.drawable.svg_ticket
    )

    data object Profile: BottomNavBarItem(
        screen = ProfileScreen(),
        icon = R.drawable.svg_profile
    )
}