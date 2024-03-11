package ru.syndicate.cinemaclub.ui.utils

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.syndicate.cinemaclub.ui.theme.CustomBlue

object CustomRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        CustomBlue,
        lightTheme = true
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        CustomBlue,
        lightTheme = true
    )

}