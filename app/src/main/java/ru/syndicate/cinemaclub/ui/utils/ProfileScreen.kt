package ru.syndicate.cinemaclub.ui.utils

import cafe.adriel.voyager.core.screen.Screen

interface ProfileScreen : Screen {

    val topBarLabel: String
        get() = ""

    val backText: String
        get() = ""

    val onClickBack: () -> Unit
        get() = { }

    val isShowBonusText: Boolean
        get() = false
}