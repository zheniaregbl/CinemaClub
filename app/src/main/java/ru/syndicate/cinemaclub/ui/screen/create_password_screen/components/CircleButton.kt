package ru.syndicate.cinemaclub.ui.screen.create_password_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.CustomRippleTheme

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    text: String = "1",
    onClick: () -> Unit = { }
) {

    CompositionLocalProvider(LocalRippleTheme provides CustomRippleTheme) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .size(80.dp)
                .background(
                    color = BlockBlack
                )
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = TextWhite
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCircleButton() {
    CircleButton()
}