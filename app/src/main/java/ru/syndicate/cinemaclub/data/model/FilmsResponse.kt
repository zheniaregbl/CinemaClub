package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("film_list")
    val listItem: List<FilmItem>
)