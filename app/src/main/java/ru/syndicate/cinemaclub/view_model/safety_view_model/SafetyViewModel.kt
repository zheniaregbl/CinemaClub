package ru.syndicate.cinemaclub.view_model.safety_view_model

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
class SafetyViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val haveAppPassword = MutableStateFlow(false)

    init {
        checkAppPassword()
    }

    fun onEvent(event: SafetyEvent) {
        when (event) {

            SafetyEvent.CheckHavePassword -> {
                checkAppPassword()
            }

            SafetyEvent.DeletePassword -> {
                deleteAppPassword()
                checkAppPassword()
            }
        }
    }

    private fun checkAppPassword() {

        viewModelScope.launch(Dispatchers.IO) {

            haveAppPassword.update {
                sharedPreferences.getBoolean("have_password", false)
            }
        }
    }

    private fun deleteAppPassword() {

        viewModelScope.launch(Dispatchers.IO) {

            sharedPreferences.edit().putBoolean("have_password", false).apply()
            sharedPreferences.edit().putString("app_password", "").apply()
        }
    }
}