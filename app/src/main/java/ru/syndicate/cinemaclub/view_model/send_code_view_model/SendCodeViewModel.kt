package ru.syndicate.cinemaclub.view_model.send_code_view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import ru.syndicate.cinemaclub.utils.isNetworkAvailable
import javax.inject.Inject

@HiltViewModel
class SendCodeViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    @ApplicationContext application: Context
) : ViewModel() {

    val sendCodeState: MutableStateFlow<BaseModel<Boolean>?> = MutableStateFlow(null)
    val context = MutableStateFlow(application)

    fun onEvent(event: SendCodeEvent) {
        when (event) {
            is SendCodeEvent.SendCode -> {
                sendCode(event.email)
            }
        }
    }

    private fun sendCode(email: String) {

        viewModelScope.launch(Dispatchers.IO) {

            sendCodeState.update { BaseModel.Loading }

            if (!isNetworkAvailable(context.value)) {
                sendCodeState.update { BaseModel.Error("Отсутствие подключения к сети") }
                return@launch
            }

            repository.sendCode(email).also { result ->

                if (result is BaseModel.Success) {
                    sendCodeState.update { BaseModel.Success(true) }
                } else if (result is BaseModel.Error) {
                    sendCodeState.update { BaseModel.Error(result.error) }
                }
            }
        }
    }
}