package ru.syndicate.cinemaclub.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.SendCodeBody
import ru.syndicate.cinemaclub.data.model.UserData
import ru.syndicate.cinemaclub.data.remote.CinemaApi
import ru.syndicate.cinemaclub.domain.repository.CinemaRepository
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor(
    private val cinemaApi: CinemaApi
) : CinemaRepository {

    override suspend fun register(body: RegisterBody): Boolean = withContext(Dispatchers.IO) {
        val response = cinemaApi.register(body)
        Log.d("registerRequest", response.message())
        response.isSuccessful
    }

    override suspend fun auth(body: AuthBody): Pair<String, UserData> = withContext(Dispatchers.IO) {
        val response = cinemaApi.auth(body)

        Log.d("authRequest", response.message())

        if (response.isSuccessful) {

            val obj = JSONObject(response.body().toString())
            val token = obj.getString("access_token")
            /*val userData = UserData(
                name = obj.getJSONObject("user_info").getString("name"),
                email = obj.getJSONObject("user_info").getString("email"),
                balance = obj.getJSONObject("user_info").getInt("balance")
            )*/

            Pair(token, UserData())
        } else {

            Pair("", UserData())
        }
    }

    override suspend fun sendCode(email: String): Boolean = withContext(Dispatchers.IO) {
        val response = cinemaApi.sendCode(SendCodeBody(email))
        Log.d("sendCodeRequest", response.message() + " " + response.code())
        response.isSuccessful
    }

    override suspend fun checkCode(body: CheckCodeBody): Boolean = withContext(Dispatchers.IO) {
        val response = cinemaApi.checkCode(body)
        Log.d("checkCodeRequest", response.message())
        response.isSuccessful
    }

    override suspend fun resetPassword(body: ResetPasswordBody): Boolean = withContext(Dispatchers.IO) {
        val response = cinemaApi.resetPassword(body)
        Log.d("resetPasswordRequest", response.message())
        if (response.isSuccessful) {
            true
        } else {
            Log.d("resetPasswordRequest", response.errorBody()!!.string())
            false
        }
    }
}