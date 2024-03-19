package ru.syndicate.cinemaclub.ui.screen.cinema.map_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.logo.Alignment
import com.yandex.mapkit.logo.HorizontalAlignment
import com.yandex.mapkit.logo.VerticalAlignment
import com.yandex.mapkit.mapview.MapView
import ru.syndicate.cinemaclub.BuildConfig
import ru.syndicate.cinemaclub.ui.utils.CinemaScreen

class MapScreen : CinemaScreen {

    override val backText: String
        get() = "< Кинотеатры"

    override val topBarLabel: String
        get() = "Карта"

    @Composable
    override fun Content() {

        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                MapView(it).apply {

                    MapKitFactory.initialize(it)

                    MapKitFactory.getInstance().onStart()

                    this.onStart()

                    this.mapWindow.map.logo.setAlignment(
                        Alignment(
                            HorizontalAlignment.LEFT,
                            VerticalAlignment.BOTTOM
                        )
                    )
                }
            }
        )
    }
}