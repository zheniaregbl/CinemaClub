package ru.syndicate.cinemaclub.view_model.profile_view_model

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
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CheckCodeBody
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.data.model.RegisterBody
import ru.syndicate.cinemaclub.data.model.ResetPasswordBody
import ru.syndicate.cinemaclub.data.model.UserData
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val user = MutableStateFlow(UserData())
    val isAuth = MutableStateFlow(false)

    init {
        checkAuth()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {

            ProfileEvent.CheckAuth -> {
                checkAuth()
            }

            ProfileEvent.Logout -> {
                logout()
                checkAuth()
            }
        }
    }

    private fun checkAuth() {
        viewModelScope.launch(Dispatchers.IO) {

            isAuth.update {
                sharedPreferences.getString("access_token", "")!! != ""
            }

            user.update {
                UserData(
                    name = sharedPreferences.getString("user_name", "")!!,
                    email = sharedPreferences.getString("user_email", "")!!,
                    balance = sharedPreferences.getInt("cinema_money", 0)
                )
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferences.edit().putString("access_token", "").apply()
            sharedPreferences.edit().putBoolean("remember_user", false).apply()
            sharedPreferences.edit().putString("user_name", "").apply()
            sharedPreferences.edit().putString("user_email", "").apply()
            sharedPreferences.edit().putBoolean("have_password", false).apply()
            sharedPreferences.edit().putString("app_password", "").apply()
        }
    }
}