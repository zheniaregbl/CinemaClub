package ru.syndicate.cinemaclub.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue

@Composable
fun LoadingLayout(
    visible: Boolean
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.5f)
                ),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .size(100.dp)
                    .background(
                        color = BlockBlack
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = CustomBlue
                )
            }
        }
    }
}