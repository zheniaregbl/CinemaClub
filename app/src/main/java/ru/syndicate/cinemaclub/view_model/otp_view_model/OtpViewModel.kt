package ru.syndicate.cinemaclub.view_model.otp_view_model

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import ru.syndicate.cinemaclub.utils.isNetworkAvailable
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext application: Context
) : ViewModel() {

    val otpState: MutableStateFlow<BaseModel<Boolean>?> = MutableStateFlow(null)
    val context = MutableStateFlow(application)

    fun onEvent(event: OtpEvent) {
        when (event) {
            is OtpEvent.CheckCode -> {
                checkCode(
                    CheckCodeBody(
                        email = event.email,
                        code = event.code
                    )
                )
            }
        }
    }

    private fun checkCode(body: CheckCodeBody) {

        viewModelScope.launch(Dispatchers.IO) {

            otpState.update { BaseModel.Loading }

            if (!isNetworkAvailable(context.value)) {
                otpState.update { BaseModel.Error("Отсутствие подключения к сети") }
                return@launch
            }

            repository.checkCode(body).also { result ->

                if (result is BaseModel.Success) {
                    sharedPreferences.edit().putString("verify_code", body.code).apply()
                    otpState.update { BaseModel.Success(true) }

                } else if (result is BaseModel.Error) {

                    otpState.update { BaseModel.Error(result.error) }
                }
            }
        }
    }
}