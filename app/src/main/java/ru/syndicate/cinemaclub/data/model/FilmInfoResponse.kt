package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class FilmInfoResponse(
    @SerializedName("film_info")
    val filmInfo: FilmItem
)
