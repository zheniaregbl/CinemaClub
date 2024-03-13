package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("balance")
    val balance: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String
)
