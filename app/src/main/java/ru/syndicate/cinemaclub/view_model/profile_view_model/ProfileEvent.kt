package ru.syndicate.cinemaclub.view_model.profile_view_model

sealed interface ProfileEvent {

    data class CheckCode(
        val email: String,
        val code: String
    ): ProfileEvent

    data object ResetOtpState: ProfileEvent

    data object CheckAuth: ProfileEvent

    data object Logout: ProfileEvent
}