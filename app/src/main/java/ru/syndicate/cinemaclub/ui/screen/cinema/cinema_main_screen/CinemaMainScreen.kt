package ru.syndicate.cinemaclub.ui.screen.cinema.cinema_main_screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.cinema.cinema_list_screen.CinemaListScreen
import ru.syndicate.cinemaclub.ui.screen.cinema.map_screen.MapScreen
import ru.syndicate.cinemaclub.ui.screen.home.home_main_screen.components.SearchLine
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.HomeStartScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BarColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.SearchLineColor
import ru.syndicate.cinemaclub.ui.utils.CinemaScreen
import ru.syndicate.cinemaclub.ui.utils.HomeScreen

class CinemaMainScreen : Screen {

    @SuppressLint("UnrememberedMutableInteractionSource")
    @Composable
    override fun Content() {

        var searchText by remember {
            mutableStateOf("")
        }

        Navigator(
            screen = CinemaListScreen()
        ) { navigator ->

            val currentScreen = navigator.lastItem as CinemaScreen

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = BarColor
                        )
                        .padding(
                            horizontal = 15.dp,
                            vertical = 10.dp
                        )
                        .statusBarsPadding(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            if (currentScreen.backText.isNotEmpty()) {

                                Text(
                                    modifier = Modifier
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null
                                        ) {
                                            navigator.pop()
                                        },
                                    text = currentScreen.backText,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = CustomGray
                                )
                            }

                            Text(
                                text = currentScreen.topBarLabel,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }

                        if (currentScreen.showMapButton) {

                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        navigator.push(
                                            MapScreen()
                                        )
                                    },
                                imageVector = ImageVector.vectorResource(R.drawable.svg_map),
                                contentDescription = null,
                                tint = CustomGray
                            )
                        }
                    }

                    SearchLine(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = SearchLineColor
                            )
                            .padding(
                                vertical = 4.dp
                            )
                            .padding(
                                start = 8.dp,
                                end = 14.dp
                            ),
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        onSearchClick = {

                        }
                    )
                }

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

@Composable
fun CinemaScreenContent(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "CinemaScreen",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewCinemaScreen() {
    CinemaScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}