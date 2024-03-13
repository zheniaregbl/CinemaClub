package ru.syndicate.cinemaclub.view_model.register_view_model

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
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import ru.syndicate.cinemaclub.utils.isNetworkAvailable
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext application: Context
) : ViewModel() {

    val uiState: MutableStateFlow<BaseModel<Boolean>?> = MutableStateFlow(null)
    val context = MutableStateFlow(application)

    fun onEvent(event: RegisterEvent) {
        when (event) {

            is RegisterEvent.RegisterUser -> {
                register(
                    RegisterBody(
                        event.name,
                        event.email,
                        event.password
                    )
                )
            }

            is RegisterEvent.ResetPassword -> {
                viewModelScope.launch(Dispatchers.IO) {

                    val verifyCode = sharedPreferences.getString("verify_code", "")!!

                    resetPassword(
                        ResetPasswordBody(
                            email = event.email,
                            code = verifyCode,
                            new_password = event.password
                        )
                    )
                }
            }
        }
    }

    private fun register(body: RegisterBody) {

        viewModelScope.launch(Dispatchers.IO) {

            uiState.update { BaseModel.Loading }

            if (!isNetworkAvailable(context.value)) {
                uiState.update { BaseModel.Error("Отсутствие подключения к сети") }
                return@launch
            }

            repository.register(body).also { result ->

                if (result is BaseModel.Success) {
                    uiState.update { BaseModel.Success(true) }
                } else if (result is BaseModel.Error) {
                    uiState.update { BaseModel.Error(result.error) }
                }
            }
        }
    }

    private fun resetPassword(body: ResetPasswordBody) {

        viewModelScope.launch(Dispatchers.IO) {

            uiState.update { BaseModel.Loading }

            if (!isNetworkAvailable(context.value)) {
                uiState.update { BaseModel.Error("Отсутствие подключения к сети") }
                return@launch
            }

            repository.resetPassword(body).also { result ->

                if (result is BaseModel.Success) {
                    uiState.update { BaseModel.Success(true) }
                    sharedPreferences.edit().putString("verify_code", "").apply()
                } else if (result is BaseModel.Error) {
                    uiState.update { BaseModel.Error(result.error) }
                }
            }
        }
    }
}