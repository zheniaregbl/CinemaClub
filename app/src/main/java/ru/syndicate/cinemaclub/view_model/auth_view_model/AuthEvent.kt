package ru.syndicate.cinemaclub.view_model.auth_view_model

sealed interface AuthEvent {

    data class AuthUser(
        val email: String,
        val password: String,
        val rememberUser: Boolean
    ): AuthEvent
}