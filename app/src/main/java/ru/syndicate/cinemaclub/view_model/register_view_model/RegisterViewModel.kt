package ru.syndicate.cinemaclub.view_model.register_view_model

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val uiState = MutableStateFlow(ProcessState())

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

            val value = repository.register(body)

            Log.d("registerRequest", "viewModel: $value")

            if (value)
                uiState.update {
                    ProcessState(
                        success = true
                    )
                }
        }
    }

    private fun resetPassword(body: ResetPasswordBody) {
        viewModelScope.launch(Dispatchers.IO) {

            val value = repository.resetPassword(body)

            if (value) {

                uiState.update {
                    ProcessState(
                        success = true
                    )
                }

                sharedPreferences.edit().putString("verify_code", "").apply()
            }
        }
    }
}