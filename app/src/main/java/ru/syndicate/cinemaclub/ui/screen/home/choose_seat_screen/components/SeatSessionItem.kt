package ru.syndicate.cinemaclub.ui.screen.home.choose_seat_screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.data.model.SeatItem

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SeatSessionItem(
    item: SeatItem = SeatItem(),
    addItem: (SeatItem) -> Unit = { },
    removeItem: (SeatItem) -> Unit = { }
) {

    var isSelected by remember {
        mutableStateOf(false)
    }

    Image(
        modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                if (!item.reservation) {

                    isSelected = !isSelected

                    if (isSelected) addItem(item) else removeItem(item)
                }
            },
        imageVector = ImageVector.vectorResource(
            id = when {
                item.reservation -> R.drawable.svg_seat_res
                !item.reservation && isSelected -> R.drawable.svg_seat_choose
                else -> R.drawable.svg_seat
            }
        ),
        contentDescription = null
    )
}

@Preview
@Composable
private fun PreviewSeatSessionItem() {
    SeatSessionItem()
}