package ru.syndicate.cinemaclub.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.AuthResponse
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.SendCodeBody

interface CinemaApi {

    @Headers("Content-Type: application/json")
    @POST("sign-up")
    suspend fun register(
        @Body body: RegisterBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("sign-in")
    suspend fun auth(
        @Body body: AuthBody
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST("forgot-password/send-code")
    suspend fun sendCode(
        @Body body: SendCodeBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("forgot-password/check-code")
    suspend fun checkCode(
        @Body body: CheckCodeBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("forgot-password/reset-password")
    suspend fun resetPassword(
        @Body body: ResetPasswordBody
    ): Response<JsonObject>
}