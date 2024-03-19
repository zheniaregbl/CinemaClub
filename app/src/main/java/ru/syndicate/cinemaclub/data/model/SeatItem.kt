package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

class SeatItem(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("reservation")
    val reservation: Boolean = false,
    @SerializedName("seat")
    val seat: Int? = 1
) {

    override fun toString(): String {
        return id.toString()
    }
}