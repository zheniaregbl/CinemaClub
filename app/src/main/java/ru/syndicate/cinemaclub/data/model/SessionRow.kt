package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class SessionRow(
    @SerializedName("row")
    val row: Int = 1,
    @SerializedName("seats")
    val listSeats: List<SeatItem> = listOf(SeatItem(), SeatItem(), SeatItem(), SeatItem())
)
