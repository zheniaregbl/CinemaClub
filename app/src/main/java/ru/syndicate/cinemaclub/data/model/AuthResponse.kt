package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val token: String,
    @SerializedName("user_info")
    val userInfo: UserInfo
)
