package ru.syndicate.cinemaclub.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.AuthResponse
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.CinemaResponse
import ru.syndicate.cinemaclub.data.model.CinemaSessionResponse
import ru.syndicate.cinemaclub.data.model.FilmInfoResponse
import ru.syndicate.cinemaclub.data.model.FilmsResponse
import ru.syndicate.cinemaclub.data.model.NewsResponse
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.SchemeModel
import ru.syndicate.cinemaclub.data.model.SendCodeBody

interface CinemaApi {

    @Headers("Content-Type: application/json")
    @POST("/api/sign-up")
    suspend fun register(
        @Body body: RegisterBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/api/sign-in")
    suspend fun auth(
        @Body body: AuthBody
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/forgot-password/send-code")
    suspend fun sendCode(
        @Body body: SendCodeBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/api/forgot-password/check-code")
    suspend fun checkCode(
        @Body body: CheckCodeBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/api/forgot-password/reset-password")
    suspend fun resetPassword(
        @Body body: ResetPasswordBody
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @GET("/api/films/main-screen")
    suspend fun getListFilms(): Response<FilmsResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/news")
    suspend fun getNews(): Response<NewsResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/cinema")
    suspend fun getCinemaInfo(): Response<CinemaResponse>

    @GET("/api/cinema/{cinema_id}/sessions")
    suspend fun getFilmSessions(
        @Path("cinema_id") id: Int,
        @Query("date") date: String
    ): Response<CinemaSessionResponse>

    @GET("/api/films/{film_id}")
    suspend fun getFilmInfo(
        @Path("film_id") id: Int
    ): Response<FilmInfoResponse>

    @GET("/api/session/{session_id}")
    suspend fun getSeatsBySession(
        @Path("session_id") id: Int
    ): Response<SchemeModel>
}