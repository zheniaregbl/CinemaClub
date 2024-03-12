package ru.syndicate.cinemaclub.ui.screen.password_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.syndicate.cinemaclub.ui.theme.CustomBlue

@Composable
fun Indicator(
    text: String = ""
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        repeat(text.length) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(16.dp)
                    .background(
                        color = CustomBlue
                    )
            )
        }

        repeat(4 - text.length) {

            Box(
                modifier = Modifier
                    .size(16.dp)
                    .border(
                        width = 1.dp,
                        color = CustomBlue,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewIndicator() {
    Indicator(
        text = "12"
    )
}