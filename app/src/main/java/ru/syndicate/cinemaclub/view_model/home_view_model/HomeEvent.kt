package ru.syndicate.cinemaclub.view_model.home_view_model


sealed interface HomeEvent {

    data class GetFilmInfo(
        val id: Int
    ): HomeEvent

    data class GetSessionScheme(
        val id: Int
    ): HomeEvent
}