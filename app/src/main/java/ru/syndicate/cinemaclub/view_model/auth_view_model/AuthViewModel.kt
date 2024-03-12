package ru.syndicate.cinemaclub.view_model.auth_view_model

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.AuthBody
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val authState = MutableStateFlow(ProcessState())

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.AuthUser -> {
                auth(
                    AuthBody(
                        email = event.email,
                        password = event.password
                    )
                )
            }

            AuthEvent.ResetState -> {
                authState.update { ProcessState() }
            }
        }
    }

    private fun auth(body: AuthBody) {
        viewModelScope.launch(Dispatchers.IO) {

            val value = repository.auth(body)

            Log.d("authRequest", value.first)

            if (value.first.isNotEmpty()) {
                authState.update {
                    ProcessState(
                        success = true
                    )
                }
                sharedPreferences.edit().putString("user_name", "some name").apply()
                sharedPreferences.edit().putString("user_email", body.email).apply()
                sharedPreferences.edit().putString("access_token", value.first).apply()
            }
        }
    }
}