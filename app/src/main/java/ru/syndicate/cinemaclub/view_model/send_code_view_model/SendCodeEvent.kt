package ru.syndicate.cinemaclub.view_model.send_code_view_model

sealed interface SendCodeEvent {

    data class SendCode(
        val email: String
    ): SendCodeEvent
}