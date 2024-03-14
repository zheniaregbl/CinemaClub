package ru.syndicate.cinemaclub.ui.screen.main_screen

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.FadeTransition
import ru.syndicate.cinemaclub.ui.bottom_nav_bar.BottomNavBar
import ru.syndicate.cinemaclub.ui.screen.home.home_main_screen.HomeScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BarColor

class MainScreen : Screen {

    @Composable
    override fun Content() {

        Navigator(
            screen = HomeScreen(),
            disposeBehavior = NavigatorDisposeBehavior(
                disposeSteps = false
            )
        ) { navigator ->

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                bottomBar = {
                    BottomNavBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = BarColor
                            )
                            .navigationBarsPadding()
                            .padding(
                                horizontal = 30.dp,
                                vertical = 10.dp
                            ),
                        navigator = navigator
                    )
                }
            ) { paddingValues ->

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                    color = BackgroundColor
                ) {

                    FadeTransition(
                        navigator = navigator,
                        animationSpec = tween(
                            durationMillis = 200,
                            easing = Ease
                        )
                    )
                }
            }
        }
    }
}