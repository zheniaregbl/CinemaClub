package ru.syndicate.cinemaclub.view_model.start_view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val haveAppPassword = MutableStateFlow(false)
    val password = MutableStateFlow("")
    val isFinishOnboarding = MutableStateFlow(false)

    init {
        checkAppPassword()
        checkFinishOnboarding()
    }

    private fun checkAppPassword() {

        viewModelScope.launch(Dispatchers.IO) {

            haveAppPassword.update {
                sharedPreferences.getBoolean("have_password", false)
            }
            password.update {
                sharedPreferences.getString("app_password", "")!!
            }
        }
    }

    private fun checkFinishOnboarding() {

        viewModelScope.launch(Dispatchers.IO) {

            isFinishOnboarding.update {
                sharedPreferences.getBoolean("finish_onboarding", false)
            }
        }
    }
}