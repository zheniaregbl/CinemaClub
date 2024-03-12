package ru.syndicate.cinemaclub.view_model.create_password_view_model

sealed interface CreatePasswordEvent {

    data class RegisterPassword(
        val newPassword: String
    ): CreatePasswordEvent
}