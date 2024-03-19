package ru.syndicate.cinemaclub.domain.repository

import com.google.gson.JsonObject
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.AuthResponse
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.CinemaResponse
import ru.syndicate.cinemaclub.data.model.CinemaSessionResponse
import ru.syndicate.cinemaclub.data.model.FilmInfoResponse
import ru.syndicate.cinemaclub.data.model.FilmsResponse
import ru.syndicate.cinemaclub.data.model.NewsResponse
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.SchemeModel

interface CinemaRepository {
    suspend fun register(body: RegisterBody): BaseModel<JsonObject>
    suspend fun auth(body: AuthBody): BaseModel<AuthResponse>
    suspend fun sendCode(email: String): BaseModel<JsonObject>
    suspend fun checkCode(body: CheckCodeBody): BaseModel<JsonObject>
    suspend fun resetPassword(body: ResetPasswordBody): BaseModel<JsonObject>
    suspend fun getListFilms(): BaseModel<FilmsResponse>
    suspend fun getNews(): BaseModel<NewsResponse>
    suspend fun getCinemaInfo(): BaseModel<CinemaResponse>
    suspend fun getFilmSessions(id: Int, date: String): BaseModel<CinemaSessionResponse>
    suspend fun getFilmInfo(id: Int): BaseModel<FilmInfoResponse>
    suspend fun getSeatsBySession(id: Int): BaseModel<SchemeModel>
}