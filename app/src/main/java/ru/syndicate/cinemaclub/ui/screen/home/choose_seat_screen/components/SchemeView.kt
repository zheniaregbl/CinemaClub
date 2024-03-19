package ru.syndicate.cinemaclub.ui.screen.home.choose_seat_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.syndicate.cinemaclub.data.model.SchemeModel
import ru.syndicate.cinemaclub.data.model.SeatItem
import ru.syndicate.cinemaclub.data.model.SessionRow

@Composable
fun SchemeView(
    modifier: Modifier = Modifier,
    schemeModel: SchemeModel = SchemeModel(),
    addItem: (SeatItem) -> Unit = { },
    removeItem: (SeatItem) -> Unit = { }
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        schemeModel.listRows.forEach { sessionRow ->

            Row(
                modifier = Modifier
                    .padding(
                        vertical = 6.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                sessionRow.listSeats.forEach { seatItem ->

                    if (seatItem.seat != null) {

                        SeatSessionItem(
                            item = seatItem,
                            addItem = addItem,
                            removeItem = removeItem
                        )
                    } else {

                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSchemeView() {
    SchemeView(
        modifier = Modifier
            .fillMaxWidth(),
        schemeModel = SchemeModel(
            listRows = listOf(
                SessionRow(
                    listSeats = listOf(
                        SeatItem(),
                        SeatItem(),
                        SeatItem(),
                        SeatItem()
                    )
                ),
                SessionRow(
                    listSeats = listOf(
                        SeatItem(),
                        SeatItem(),
                        SeatItem(
                            seat = null
                        ),
                        SeatItem(),
                        SeatItem(),
                        SeatItem(),
                        SeatItem(),
                        SeatItem(),
                        SeatItem(
                            seat = null
                        ),
                        SeatItem(),
                        SeatItem()
                    )
                )
            )
        )
    )
}