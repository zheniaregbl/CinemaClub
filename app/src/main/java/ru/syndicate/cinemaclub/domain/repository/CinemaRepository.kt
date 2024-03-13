package ru.syndicate.cinemaclub.domain.repository

import com.google.gson.JsonObject
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.AuthResponse
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.UserData

interface CinemaRepository {
    suspend fun register(body: RegisterBody): BaseModel<JsonObject>
    suspend fun auth(body: AuthBody): BaseModel<AuthResponse>
    suspend fun sendCode(email: String): BaseModel<JsonObject>
    suspend fun checkCode(body: CheckCodeBody): BaseModel<JsonObject>
    suspend fun resetPassword(body: ResetPasswordBody): BaseModel<JsonObject>
}