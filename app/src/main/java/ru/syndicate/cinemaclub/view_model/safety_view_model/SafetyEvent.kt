package ru.syndicate.cinemaclub.view_model.safety_view_model

sealed interface SafetyEvent {

    data object CheckHavePassword: SafetyEvent
    data object DeletePassword: SafetyEvent
}