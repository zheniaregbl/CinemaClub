package ru.syndicate.cinemaclub.view_model.cinema_view_model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.CinemaItem
import ru.syndicate.cinemaclub.data.model.FilmSession
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CinemaViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl
) : ViewModel() {

    val listCinema: MutableStateFlow<BaseModel<List<CinemaItem>>?> = MutableStateFlow(null)
    val filmSessions: MutableStateFlow<BaseModel<List<FilmSession>>?> = MutableStateFlow(null)

    init {
        getCinemaInfo()
    }

    fun onEvent(event: CinemaEvent) {
        when (event) {
            is CinemaEvent.GetFilmSession -> {
                getFilmSessions(event.date, event.id)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFilmSessions(date: LocalDate, id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val dateString = "${date.year}-${date.monthValue}-${date.dayOfMonth}"

            Log.d("checkCinema", dateString)

            filmSessions.update { BaseModel.Loading }

            repository.getFilmSessions(id, dateString).also { result ->

                if (result is BaseModel.Success) {
                    filmSessions.update { BaseModel.Success(result.data.listItem) }
                    Log.d("checkSession", result.data.listItem.size.toString())
                } else if (result is BaseModel.Error) {
                    filmSessions.update { BaseModel.Error(result.error) }
                    Log.d("checkSession", result.error)
                }
            }
        }
    }

    private fun getCinemaInfo() {

        viewModelScope.launch(Dispatchers.IO) {

            listCinema.update { BaseModel.Loading }

            repository.getCinemaInfo().also { result ->

                if (result is BaseModel.Success) {
                    listCinema.update { BaseModel.Success(result.data.listItems) }
                } else if (result is BaseModel.Error) {
                    listCinema.update { BaseModel.Error(result.error) }
                }
            }
        }
    }
}