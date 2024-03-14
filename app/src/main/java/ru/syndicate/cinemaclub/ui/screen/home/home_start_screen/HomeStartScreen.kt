package ru.syndicate.cinemaclub.ui.screen.home.home_start_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.NewsPage
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.NewsPager
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.utils.HomeScreen

class HomeStartScreen : HomeScreen {

    override val topBarLabel: String
        get() = "Главная"

    @Composable
    override fun Content() {

        HomeStartScreenContent(
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeStartScreenContent(
    modifier: Modifier = Modifier
) {

    val list = listOf(
        "https://моиотзывы.рф/wp-content/uploads/2021/11/1238347290_37082_neon_genesis_evangelion-scaled.jpg",
        "https://pm1.aminoapps.com/6798/7bf71f3d8a330284feb31567f75a238712bcfef1v2_hq.jpg",
        "https://gas-kvas.com/uploads/posts/2023-01/1673333614_gas-kvas-com-p-anime-risunki-mob-psikho-100-4.jpg"
    )

    LazyColumn(
        modifier = modifier
    ) {

        item {

            NewsPager(
                modifier = Modifier
                    .padding(
                        top = 8.dp
                    )
                    .height(180.dp),
                list = list
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeStartScreen() {
    HomeStartScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}