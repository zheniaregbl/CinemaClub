package ru.syndicate.cinemaclub.view_model.ticket_view_model

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val isAuth = MutableStateFlow(false)

    fun onEvent(event: TicketEvent) {
        when (event) {
            TicketEvent.CheckAuth -> checkAuth()
        }
    }

    private fun checkAuth() {

        viewModelScope.launch(Dispatchers.IO) {

            isAuth.update {
                sharedPreferences.getString("access_token", "")!! != ""
            }
        }
    }
}