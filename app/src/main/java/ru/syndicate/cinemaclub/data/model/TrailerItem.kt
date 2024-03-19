package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class TrailerItem(
    @SerializedName("trailer")
    val trailer: String = "/static/trailers/djentlmeni-udaci-1971-treyler.mp4"
)
