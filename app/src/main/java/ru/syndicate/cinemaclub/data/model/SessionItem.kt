package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class SessionItem(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("cinema_type")
    val type: String = "2D",
    @SerializedName("hall")
    val hall: String = "Зал-1",
    @SerializedName("price")
    val price: Int = 250,
    @SerializedName("time")
    val time: String = "17:00"
)
