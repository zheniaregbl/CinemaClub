package ru.syndicate.cinemaclub.ui.utils

import cafe.adriel.voyager.core.screen.Screen

interface CinemaScreen : Screen {

    val topBarLabel: String
        get() = ""

    val backText: String
        get() = ""

    val showMapButton: Boolean
        get() = false

    val showSearchLine: Boolean
        get() = false
}