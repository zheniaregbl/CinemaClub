package ru.syndicate.cinemaclub.view_model.profile_view_model

sealed interface ProfileEvent {

    data object CheckAuth: ProfileEvent

    data object Logout: ProfileEvent
}