package ru.syndicate.cinemaclub.view_model.auth_view_model

import ru.syndicate.cinemaclub.view_model.profile_view_model.ProfileEvent

sealed interface AuthEvent {

    data class AuthUser(
        val email: String,
        val password: String
    ): AuthEvent

    data object ResetState: AuthEvent
}