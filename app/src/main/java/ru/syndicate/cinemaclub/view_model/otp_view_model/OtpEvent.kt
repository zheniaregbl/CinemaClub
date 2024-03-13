package ru.syndicate.cinemaclub.view_model.otp_view_model

sealed interface OtpEvent {

    data class CheckCode(
        val email: String,
        val code: String
    ): OtpEvent
}