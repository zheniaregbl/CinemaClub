package ru.syndicate.cinemaclub.view_model.register_view_model

import ru.syndicate.cinemaclub.view_model.profile_view_model.ProfileEvent

sealed interface RegisterEvent {

    data class RegisterUser(
        val name: String,
        val email: String,
        val password: String
    ): RegisterEvent

    data class ResetPassword(
        val email: String,
        val password: String
    ): RegisterEvent
}