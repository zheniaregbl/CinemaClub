package ru.syndicate.cinemaclub.ui.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.OnboardingScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor

class SplashScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        SplashScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            navigateToNext = {
                navigator.replace(OnboardingScreen())
            }
        )
    }
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    navigateToNext: () -> Unit = { }
) {

    LaunchedEffect(Unit) {
        delay(1500)
        navigateToNext()
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.svg_splash_logo),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}