package ru.syndicate.cinemaclub.view_model.send_code_view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class SendCodeViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val sendCodeState = MutableStateFlow(ProcessState())

    fun onEvent(event: SendCodeEvent) {
        when (event) {
            is SendCodeEvent.SendCode -> {
                sendCode(event.email)
            }
        }
    }

    private fun sendCode(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val value = repository.sendCode(email)

            if (value)
                sendCodeState.update {
                    ProcessState(
                        success = true
                    )
                }
        }
    }
}