package ru.syndicate.cinemaclub.ui.screen.home.home_start_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.data.model.NewsItem
import ru.syndicate.cinemaclub.ui.screen.home.film_screen.FilmScreen
import ru.syndicate.cinemaclub.ui.screen.home.all_film_screen.AllFilmScreen
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.FilmCardInfo
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.NewsPager
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.NewsPagerIndicator
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.HomeScreen
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeViewModel

class HomeStartScreen : HomeScreen {

    override val topBarLabel: String
        get() = "Главная"

    override val isShowSearchLine: Boolean
        get() = false

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val homeViewModel = getViewModel<HomeViewModel>()

        val listFilm by homeViewModel.films.collectAsState()
        val listNews by homeViewModel.news.collectAsState()

        HomeStartScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            listFilms = listFilm,
            listNews = listNews,
            navigateToAllFilm = {
                navigator.push(
                    AllFilmScreen()
                )
            },
            navigateToFilm = { film ->
                navigator.push(
                    FilmScreen(
                        film
                    )
                )
            }
        )
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun HomeStartScreenContent(
    modifier: Modifier = Modifier,
    listFilms: BaseModel<List<FilmItem>>? = BaseModel.Success(listOf()),
    listNews: BaseModel<List<NewsItem>>? = BaseModel.Success(listOf()),
    navigateToAllFilm: () -> Unit = { },
    navigateToFilm: (FilmItem) -> Unit = { }
) {

    val pagerState = rememberPagerState(
        initialPage = if (listNews is BaseModel.Success) listNews.data.size * 400 / 2 else 6 * 400 / 2,
        pageCount = {
            if (listNews is BaseModel.Success) listNews.data.size * 400 else 400
        }
    )

    LazyColumn(
        modifier = modifier
    ) {

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (listNews is BaseModel.Success) {

                    NewsPager(
                        modifier = Modifier
                            .height(180.dp),
                        list = listNews.data,
                        pagerState = pagerState
                    )

                    NewsPagerIndicator(
                        pageCount = listNews.data.size,
                        currentPage = pagerState.currentPage % listNews.data.size
                    )
                }
            }
        }

        item {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "В кино",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextWhite
                )

                Text(
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) { navigateToAllFilm() },
                    text = "Все фильмы >",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = CustomGray
                )
            }
        }

        item {

            if (listFilms is BaseModel.Success) {

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                    maxItemsInEachRow = 2,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    listFilms.data.forEach {

                        FilmCardInfo(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .clickable { navigateToFilm(it) },
                            item = it
                        )
                    }

                    if (listFilms.data.size % 2 != 0) {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                }
            }
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