package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class SchemeModel(
    @SerializedName("scheme")
    val listRows: List<SessionRow> = listOf(SessionRow(), SessionRow(), SessionRow(), SessionRow())
)
