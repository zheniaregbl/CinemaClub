package ru.syndicate.cinemaclub.ui.extansions

fun String.containsUnwantedChar(): Boolean =
    contains(' ') || contains('/') || contains('\\') || contains('\"') ||
            contains('\'')