package ru.syndicate.cinemaclub.ui.screen.cinema.cinema_session_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.syndicate.cinemaclub.core.CoreConstants
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.FilmSession
import ru.syndicate.cinemaclub.ui.screen.cinema.cinema_session_screen.components.DateRow
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.CinemaScreen
import ru.syndicate.cinemaclub.view_model.cinema_view_model.CinemaEvent
import ru.syndicate.cinemaclub.view_model.cinema_view_model.CinemaViewModel
import java.time.LocalDate

data class CinemaSessionScreen(
    val id: Int
) : CinemaScreen {

    override val topBarLabel: String
        get() = "Расписание"

    override val backText: String
        get() = "< Кинотеатры"

    override val showSearchLine: Boolean
        get() = false

    @Composable
    override fun Content() {

        val cinemaViewModel = getViewModel<CinemaViewModel>()

        val filmSessions by cinemaViewModel.filmSessions.collectAsState()

        val selectedDate = remember {
            mutableStateOf(LocalDate.now())
        }

        LaunchedEffect(Unit) {
            cinemaViewModel.onEvent(CinemaEvent.GetFilmSession(selectedDate.value, id))
        }

        LaunchedEffect(selectedDate.value) {
            cinemaViewModel.onEvent(CinemaEvent.GetFilmSession(selectedDate.value, id))
        }

        CinemaSessionScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            selectedDate = selectedDate,
            listSessions = filmSessions
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CinemaSessionScreenContent(
    modifier: Modifier = Modifier,
    selectedDate: MutableState<LocalDate> = mutableStateOf(LocalDate.now()),
    listSessions: BaseModel<List<FilmSession>>? = BaseModel.Success(listOf())
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        DateRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 10.dp,
                    vertical = 5.dp
                ),
            selectedDate = selectedDate
        )

        when {

            listSessions is BaseModel.Success && listSessions.data.isNotEmpty() -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        vertical = 10.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(listSessions.data) { film ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 10.dp
                                ),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 16.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                AsyncImage(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .size(
                                            width = 50.dp,
                                            height = 62.dp
                                        ),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(CoreConstants.BASE_URL + film.poster)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {

                                    Text(
                                        text = film.title,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {

                                        FlowRow(
                                            modifier = Modifier
                                                .width(150.dp),
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {

                                            for (i in film.genre.indices) {

                                                var textGenre = film.genre[i].title

                                                if (i != film.genre.lastIndex) textGenre += ","

                                                Text(
                                                    text = textGenre,
                                                    style = MaterialTheme.typography.labelMedium,
                                                    fontWeight = FontWeight.Normal,
                                                    fontSize = 14.sp,
                                                    color = CustomGray
                                                )
                                            }
                                        }

                                        Text(
                                            text = "•",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = CustomGray
                                        )

                                        Text(
                                            text = "${film.timing} мин.",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = CustomGray
                                        )

                                        Text(
                                            text = "•",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = CustomGray
                                        )

                                        Text(
                                            text = film.age,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            color = CustomGray
                                        )
                                    }
                                }
                            }

                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {

                                items(film.session) { session ->

                                    Column(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(
                                                color = BlockBlack
                                            )
                                            .padding(10.dp)
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    bottom = 4.dp
                                                ),
                                            text = session.time,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 24.sp,
                                            color = TextWhite
                                        )

                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    bottom = 11.dp
                                                ),
                                            text = session.time,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 12.sp,
                                            color = CustomGray
                                        )

                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    bottom = 4.dp
                                                ),
                                            text = "${session.price} руб.",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 12.sp,
                                            color = CustomGray
                                        )

                                        Text(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(5.dp))
                                                .background(
                                                    color = CustomGray
                                                )
                                                .padding(
                                                    horizontal = 3.dp,
                                                    vertical = 2.dp
                                                ),
                                            text = session.type,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 10.sp,
                                            color = BlockBlack
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            listSessions is BaseModel.Success && listSessions.data.isEmpty() -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "На этот день нету фильмов",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = TextWhite
                    )
                }
            }

            listSessions is BaseModel.Loading -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = CustomBlue
                    )
                }
            }
        }
    }
}