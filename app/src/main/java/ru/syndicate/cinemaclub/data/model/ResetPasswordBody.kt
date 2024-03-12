package ru.syndicate.cinemaclub.data.model

data class ResetPasswordBody(
    val email: String,
    val code: String,
    val new_password: String
)
