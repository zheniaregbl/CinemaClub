package ru.syndicate.cinemaclub.ui.screen.home.film_screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.syndicate.cinemaclub.core.CoreConstants
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.ui.screen.home.film_screen.components.TrailerPlayer
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.LightGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.HomeScreen
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeEvent
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeViewModel

data class FilmScreen(
    val filmItem: FilmItem
) : HomeScreen {

    override val isShowSearchLine: Boolean
        get() = false

    override val backText: String
        get() = "< Все фильмы"

    override val topBarLabel: String
        get() = filmItem.title

    @Composable
    override fun Content() {

        val homeViewModel = getViewModel<HomeViewModel>()

        val filmInfo by homeViewModel.filmInfo.collectAsState()

        LaunchedEffect(Unit) {
            homeViewModel.onEvent(HomeEvent.GetFilmInfo(filmItem.id))
        }

        FilmScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = BackgroundColor
                ),
            filmItem = filmInfo
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilmScreenContent(
    modifier: Modifier = Modifier,
    filmItem: BaseModel<FilmItem>? = BaseModel.Success(FilmItem())
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {

            if (filmItem is BaseModel.Success) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 16.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            Text(
                                text = filmItem.data.title,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {

                                Text(
                                    text = filmItem.data.engTitle ?: "",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )

                                FlowRow(
                                    modifier = Modifier
                                        .width(300.dp),
                                    maxItemsInEachRow = 4,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Text(
                                        text = filmItem.data.year + ", ",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Center,
                                        color = CustomGray
                                    )

                                    for (i in filmItem.data.genre.indices) {

                                        val genreText =
                                            if (i == filmItem.data.genre.lastIndex) filmItem.data.genre[i].title
                                            else filmItem.data.genre[i].title + ", "

                                        Text(
                                            text = genreText,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center,
                                            color = CustomGray
                                        )
                                    }
                                }

                                Text(
                                    text = "${filmItem.data.country}, ${filmItem.data.timing} мин, ${filmItem.data.age}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center,
                                    color = CustomGray
                                )
                            }
                        }
                    }

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 16.dp
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { }
                                    .background(
                                        color = CustomBlue
                                    )
                                    .padding(10.dp),
                                text = "Расписание и билеты",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextWhite
                            )
                        }
                    }

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = filmItem.data.reviewTitle ?: "",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = filmItem.data.reviewText,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = LightGray
                            )
                        }
                    }

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = "Трейлер",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextWhite
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {

                                TrailerPlayer(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(15.dp))
                                        .size(
                                            width = 302.dp,
                                            height = 170.dp
                                        ),
                                    url = CoreConstants.BASE_URL + filmItem.data.listTrailers.first().trailer
                                )
                            }
                        }
                    }
                }
            }
        },
        sheetShape = RectangleShape,
        sheetContainerColor = BackgroundColor,
        sheetDragHandle = {
            BottomSheetDefaults.DragHandle(
                width = 40.dp,
                height = 3.dp,
                color = CustomGray
            )
        },
        sheetPeekHeight = 160.dp
    ) {

        if (filmItem is BaseModel.Success) {

            val request = ImageRequest.Builder(LocalContext.current)
                .data(CoreConstants.BASE_URL + filmItem.data.poster)
                .crossfade(true)
                .build()

            Box(
                modifier = modifier
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(
                            radiusX = 5.dp,
                            radiusY = 5.dp,
                            edgeTreatment = BlurredEdgeTreatment.Unbounded
                        ),
                    model = request,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) Color.Black.copy(
                                alpha = 0.6f
                            )
                            else Color.Transparent
                        )
                )

                AsyncImage(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            bottom = 100.dp
                        )
                        .clip(RoundedCornerShape(13.dp))
                        .size(
                            width = 346.dp,
                            height = 420.dp
                        ),
                    model = request,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}