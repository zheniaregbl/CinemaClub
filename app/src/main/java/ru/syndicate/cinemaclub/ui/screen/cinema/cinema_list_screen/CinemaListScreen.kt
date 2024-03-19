package ru.syndicate.cinemaclub.ui.screen.cinema.cinema_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CinemaItem
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.ui.screen.cinema.cinema_list_screen.components.CinemaListItem
import ru.syndicate.cinemaclub.ui.screen.cinema.cinema_session_screen.CinemaSessionScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.utils.CinemaScreen
import ru.syndicate.cinemaclub.view_model.cinema_view_model.CinemaViewModel

class CinemaListScreen : CinemaScreen {

    override val topBarLabel: String
        get() = "Кинотеатры"

    override val showMapButton: Boolean
        get() = true

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val cinemaViewModel = getViewModel<CinemaViewModel>()

        val listCinema by cinemaViewModel.listCinema.collectAsState()

        CinemaListScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp
                ),
            listItems = listCinema,
            navigateToSessions = { id ->
                navigator.push(
                    CinemaSessionScreen(id)
                )
            }
        )
    }
}

@Composable
fun CinemaListScreenContent(
    modifier: Modifier = Modifier,
    listItems: BaseModel<List<CinemaItem>>? = BaseModel.Success(listOf()),
    navigateToSessions: (Int) -> Unit = { }
) {

    if (listItems is BaseModel.Success) {

        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
            }

            items(listItems.data) {

                CinemaListItem(
                    modifier = Modifier
                        .clickable { navigateToSessions(it.id) }
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(
                            color = BlockBlack
                        )
                        .padding(10.dp),
                    item = it
                )
            }

            item {

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
            }
        }
    }
}