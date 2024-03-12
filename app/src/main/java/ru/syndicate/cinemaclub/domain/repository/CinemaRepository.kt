package ru.syndicate.cinemaclub.domain.repository

import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.UserData

interface CinemaRepository {
    suspend fun register(body: RegisterBody): Boolean
    suspend fun auth(body: AuthBody): Pair<String, UserData>
    suspend fun sendCode(email: String): Boolean
    suspend fun checkCode(body: CheckCodeBody): Boolean
    suspend fun resetPassword(body: ResetPasswordBody): Boolean
}