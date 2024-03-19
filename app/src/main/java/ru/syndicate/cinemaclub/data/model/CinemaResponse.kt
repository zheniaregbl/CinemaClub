package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class CinemaResponse(
    @SerializedName("cinema_list")
    val listItems: List<CinemaItem>
)