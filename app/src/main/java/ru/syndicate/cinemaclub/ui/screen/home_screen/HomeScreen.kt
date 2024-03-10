package ru.syndicate.cinemaclub.ui.screen.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.utils.CustomScreen

class HomeScreen : CustomScreen {

    override val topBarLabel: String
        get() = "Главная"

    @Composable
    override fun Content() {

        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun HomeScreenContent(
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
                text = "HomeScreen",
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}