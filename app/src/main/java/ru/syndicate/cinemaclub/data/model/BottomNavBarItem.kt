package ru.syndicate.cinemaclub.data.model

import cafe.adriel.voyager.core.screen.Screen
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.home.home_main_screen.HomeScreen
import ru.syndicate.cinemaclub.ui.screen.cinema.cinema_main_screen.CinemaMainScreen
import ru.syndicate.cinemaclub.ui.screen.profile.profile_main_screen.ProfileMainScreen
import ru.syndicate.cinemaclub.ui.screen.ticket.ticket_screen.TicketScreen

sealed class BottomNavBarItem(
    val screen: Screen,
    val icon: Int
) {

    data object Home: BottomNavBarItem(
        screen = HomeScreen(),
        icon = R.drawable.svg_home
    )

    data object Cinema: BottomNavBarItem(
        screen = CinemaMainScreen(),
        icon = R.drawable.svg_point
    )

    data object Ticket: BottomNavBarItem(
        screen = TicketScreen(),
        icon = R.drawable.svg_ticket
    )

    data object Profile: BottomNavBarItem(
        screen = ProfileMainScreen(),
        icon = R.drawable.svg_profile
    )
}