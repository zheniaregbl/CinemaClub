package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class CinemaItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("title")
    val title: String
)
