package ru.syndicate.cinemaclub.data.repository

import com.google.gson.JsonObject
import retrofit2.Response
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
import ru.syndicate.cinemaclub.data.model.SendCodeBody
import ru.syndicate.cinemaclub.data.remote.CinemaApi
import ru.syndicate.cinemaclub.domain.repository.CinemaRepository
import java.lang.Exception
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor(
    private val cinemaApi: CinemaApi
) : CinemaRepository {

    override suspend fun register(body: RegisterBody): BaseModel<JsonObject> {
        return request {
            cinemaApi.register(body)
        }
    }

    override suspend fun auth(body: AuthBody): BaseModel<AuthResponse> {
        return request {
            cinemaApi.auth(body)
        }
    }

    override suspend fun sendCode(email: String): BaseModel<JsonObject> {
        return request {
            cinemaApi.sendCode(
                SendCodeBody(email)
            )
        }
    }

    override suspend fun checkCode(body: CheckCodeBody): BaseModel<JsonObject> {
        return request {
            cinemaApi.checkCode(body)
        }
    }

    override suspend fun resetPassword(body: ResetPasswordBody): BaseModel<JsonObject> {
        return request {
            cinemaApi.resetPassword(body)
        }
    }

    override suspend fun getListFilms(): BaseModel<FilmsResponse> {
        return request {
            cinemaApi.getListFilms()
        }
    }

    override suspend fun getNews(): BaseModel<NewsResponse> {
        return request {
            cinemaApi.getNews()
        }
    }

    override suspend fun getCinemaInfo(): BaseModel<CinemaResponse> {
        return request {
            cinemaApi.getCinemaInfo()
        }
    }

    override suspend fun getFilmSessions(
        id: Int,
        date: String
    ): BaseModel<CinemaSessionResponse> {
        return request {
            cinemaApi.getFilmSessions(id, date)
        }
    }

    override suspend fun getFilmInfo(id: Int): BaseModel<FilmInfoResponse> {
        return request {
            cinemaApi.getFilmInfo(id)
        }
    }

    override suspend fun getSeatsBySession(id: Int): BaseModel<SchemeModel> {
        return request {
            cinemaApi.getSeatsBySession(id)
        }
    }
}

suspend fun<T> request(request: suspend ()-> Response<T>): BaseModel<T> {
    try {
        request().also {
            return if (it.isSuccessful) {
                BaseModel.Success(it.body()!!)
            } else {
                BaseModel.Error(it.errorBody()?.string().toString())
            }
        }
    } catch (e: Exception) {
        return BaseModel.Error(e.message.toString())
    }
}