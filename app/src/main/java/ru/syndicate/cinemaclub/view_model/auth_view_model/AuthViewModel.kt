package ru.syndicate.cinemaclub.view_model.auth_view_model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import ru.syndicate.cinemaclub.utils.isNetworkAvailable
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext application: Context
) : ViewModel() {

    val authState: MutableStateFlow<BaseModel<Boolean>?> = MutableStateFlow(null)
    val context = MutableStateFlow(application)

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.AuthUser -> {
                auth(
                    AuthBody(
                        email = event.email,
                        password = event.password
                    ),
                    rememberUser = event.rememberUser
                )
            }
        }
    }

    private fun auth(body: AuthBody, rememberUser: Boolean) {

        viewModelScope.launch(Dispatchers.IO) {

            authState.update { BaseModel.Loading }

            if (!isNetworkAvailable(context.value)) {
                authState.update { BaseModel.Error("Отсутствие подключения к сети") }
                return@launch
            }

            repository.auth(body).also { result ->

                if (result is BaseModel.Success) {

                    Log.d("authRequest", result.data.token)

                    sharedPreferences.edit().putString("user_name", result.data.userInfo.name).apply()
                    sharedPreferences.edit().putString("user_email", result.data.userInfo.email).apply()
                    sharedPreferences.edit().putInt("cinema_money", result.data.userInfo.balance).apply()
                    sharedPreferences.edit().putBoolean("remember_user", rememberUser).apply()
                    sharedPreferences.edit().putString("access_token", result.data.token).apply()

                    authState.update { BaseModel.Success(true) }

                } else if (result is BaseModel.Error) {
                    authState.update { BaseModel.Error(result.error) }
                }
            }
        }
    }
}