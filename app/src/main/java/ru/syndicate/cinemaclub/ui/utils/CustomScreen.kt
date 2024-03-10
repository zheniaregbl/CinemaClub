package ru.syndicate.cinemaclub.ui.utils

import cafe.adriel.voyager.core.screen.Screen

interface CustomScreen : Screen {
    val topBarLabel: String
        get() = ""

    val backScreenText: String
        get() = ""
}