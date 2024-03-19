package ru.syndicate.cinemaclub.ui.screen.home.choose_seat_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.hilt.getViewModel
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.SchemeModel
import ru.syndicate.cinemaclub.data.model.SeatItem
import ru.syndicate.cinemaclub.ui.screen.home.choose_seat_screen.components.SchemeView
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.utils.HomeScreen
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeEvent
import ru.syndicate.cinemaclub.view_model.home_view_model.HomeViewModel

class ChooseSeatScreen : HomeScreen {

    override val isShowSearchLine: Boolean
        get() = false

    override val topBarLabel: String
        get() = "Выбор места"

    @Composable
    override fun Content() {

        val homeViewModel = getViewModel<HomeViewModel>()

        val sessionScheme by homeViewModel.sessionScheme.collectAsState()

        LaunchedEffect(Unit) {
            homeViewModel.onEvent(HomeEvent.GetSessionScheme(1))
        }

        ChooseSeatScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            scheme = sessionScheme
        )
    }
}

@Composable
fun ChooseSeatScreenContent(
    modifier: Modifier = Modifier,
    scheme: BaseModel<SchemeModel>? = BaseModel.Success(SchemeModel())
) {

    val selectedSeats = remember {
        mutableStateListOf<SeatItem>()
    }

    LaunchedEffect(selectedSeats.size) {
        Log.d("checkSelectedSeats", selectedSeats.toList().joinToString(", "))
    }

    if (scheme is BaseModel.Success) {

        Column(
            modifier = modifier
        ) {

            SchemeView(
                modifier = Modifier
                    .fillMaxWidth(),
                schemeModel = scheme.data,
                addItem = {
                    selectedSeats.add(it)
                },
                removeItem = {
                    selectedSeats.remove(it)
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewChooseSeatScreen() {
    ChooseSeatScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}