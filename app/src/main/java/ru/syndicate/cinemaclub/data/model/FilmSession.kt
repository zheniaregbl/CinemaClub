package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class FilmSession(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "Семь",
    @SerializedName("genre")
    val genre: List<GenreItem> = listOf(GenreItem(), GenreItem(), GenreItem()),
    @SerializedName("age")
    val age: String = "18+",
    @SerializedName("timing")
    val timing: Int = 100,
    @SerializedName("poster")
    val poster: String = "/static/poster/3838.jpg",
    @SerializedName("sessions")
    val session: List<SessionItem> = listOf(SessionItem(), SessionItem(), SessionItem(), SessionItem(), SessionItem(), SessionItem(), SessionItem())
)
