package ru.syndicate.cinemaclub.data.model

data class RegisterBody(
    val name: String,
    val email: String,
    val password: String
)