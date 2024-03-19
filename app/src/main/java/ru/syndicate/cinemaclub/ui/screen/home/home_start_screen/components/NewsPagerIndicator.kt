package ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.LightGray

@Composable
fun NewsPagerIndicator(
    pageCount: Int,
    currentPage: Int
) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {

        repeat(pageCount) { iteration ->

            val color = if (currentPage == iteration) LightGray else CustomGray

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 4.dp
                    )
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(
                        color = color
                    )
            )
        }
    }
}