package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class RequestMessage(
    @SerializedName("message")
    val message: String
)
