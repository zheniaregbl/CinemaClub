package ru.syndicate.cinemaclub.ui.screen.home.all_film_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components.FilmCardInfo
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.utils.HomeScreen
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeViewModel

class AllFilmScreen : HomeScreen {

    override val topBarLabel: String
        get() = "Все фильмы"

    override val backText: String
        get() = "< Главная"

    @Composable
    override fun Content() {

        val homeViewModel = getViewModel<HomeViewModel>()

        val films by homeViewModel.films.collectAsState()

        AllFilmScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            listFilms = films
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllFilmScreenContent(
    modifier: Modifier = Modifier,
    listFilms: BaseModel<List<FilmItem>>? = BaseModel.Success(listOf())
) {

    Column(
        modifier = modifier
    ) {

        LazyColumn {

            item {

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

                    if (listFilms is BaseModel.Success) {

                        listFilms.data.forEach {

                            FilmCardInfo(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
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
}