package ru.syndicate.cinemaclub.ui.screen.splash_screen

import android.os.CancellationSignal
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.main_screen.MainScreen
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.OnboardingScreen
import ru.syndicate.cinemaclub.ui.screen.verify_enter_screen.VerifyEnterScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.view_model.start_view_model.StartViewModel
import java.util.concurrent.Executor

data class SplashScreen(
    val checkBiometricSupport: () -> Boolean,
    val getCancellationSignal: () -> CancellationSignal,
    val executor: Executor
) : Screen {

    @Composable
    override fun Content() {

        val startViewModel = getViewModel<StartViewModel>()

        val haveAppPassword by startViewModel.haveAppPassword.collectAsState()
        val password by startViewModel.password.collectAsState()
        val isFinishOnboarding by startViewModel.isFinishOnboarding.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        SplashScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            navigateToNext = {
                navigator.replace(
                    if (haveAppPassword) {
                        VerifyEnterScreen(
                            checkBiometricSupport = checkBiometricSupport,
                            getCancellationSignal = getCancellationSignal,
                            executor = executor,
                            rightPassword = password
                        )
                    } else {
                        if (isFinishOnboarding) MainScreen() else OnboardingScreen()
                    }
                )
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