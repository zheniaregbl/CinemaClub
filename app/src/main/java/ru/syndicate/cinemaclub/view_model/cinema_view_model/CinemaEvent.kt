package ru.syndicate.cinemaclub.view_model.cinema_view_model

import java.time.LocalDate

sealed interface CinemaEvent {

    data class GetFilmSession(
        val date: LocalDate,
        val id: Int
    ): CinemaEvent
}