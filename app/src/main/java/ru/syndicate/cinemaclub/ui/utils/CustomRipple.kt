package ru.syndicate.cinemaclub.ui.utils

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class CustomRipple(private val color: Color): RippleTheme {
    @Composable
    override fun defaultColor() = color

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 1f,
        focusedAlpha = 1f,
        hoveredAlpha = 1f,
        pressedAlpha = 1f
    )
}