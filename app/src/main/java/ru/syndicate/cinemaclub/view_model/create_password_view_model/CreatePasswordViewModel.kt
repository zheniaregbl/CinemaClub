package ru.syndicate.cinemaclub.view_model.create_password_view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePasswordViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun onEvent(event: CreatePasswordEvent) {
        when (event) {
            is CreatePasswordEvent.RegisterPassword -> {
                registerNewPassword(event.newPassword)
            }
        }
    }

    private fun registerNewPassword(password: String) {

        viewModelScope.launch(Dispatchers.IO) {

            sharedPreferences.edit().putBoolean("have_password", true).apply()
            sharedPreferences.edit().putString("app_password", password).apply()
        }
    }
}