package ru.syndicate.cinemaclub.view_model.ticket_view_model

sealed interface TicketEvent {

    data object CheckAuth: TicketEvent
}