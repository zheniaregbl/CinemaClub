package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class CinemaSessionResponse(
    @SerializedName("films_list")
    val listItem: List<FilmSession>
)