package ru.syndicate.cinemaclub.ui.screen.main_screen

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import ru.syndicate.cinemaclub.ui.bottom_nav_bar.BottomNavBar
import ru.syndicate.cinemaclub.ui.screen.home_screen.HomeScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BarColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.utils.CustomScreen

class MainScreen : Screen {

    @Composable
    override fun Content() {

        Navigator(HomeScreen()) { navigator ->

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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = BarColor
                            )
                            .statusBarsPadding()
                            .padding(
                                horizontal = 30.dp,
                                vertical = 10.dp
                            )
                    ) {

                        val currentScreen = navigator.lastItem as CustomScreen

                        AnimatedContent(
                            targetState = currentScreen,
                            label = "topBarText",
                            transitionSpec = {
                                fadeIn(
                                    animationSpec = tween(
                                        durationMillis = 200,
                                        easing = Ease
                                    )
                                ) togetherWith fadeOut(
                                    animationSpec = tween(
                                        durationMillis = 200,
                                        easing = Ease
                                    )
                                )
                            }
                        ) { screen ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                if (currentScreen.backScreenText.isNotEmpty()) {

                                    Text(
                                        text = screen.backScreenText,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = CustomGray
                                    )
                                }

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = screen.topBarLabel,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

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
}