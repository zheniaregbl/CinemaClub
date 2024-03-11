package ru.syndicate.cinemaclub.ui.screen.onboarding_screen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ru.syndicate.cinemaclub.R

@Composable
fun ImageBackground(
    state: Animatable<Float, AnimationVector1D>
) {

    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = R.drawable.onboarding_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = BiasAlignment(state.value, 0f)
    )
}