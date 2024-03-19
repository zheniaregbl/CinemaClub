package ru.syndicate.cinemaclub.view_model.home_view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.data.model.BaseModel
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.data.model.NewsItem
import ru.syndicate.cinemaclub.data.model.SchemeModel
import ru.syndicate.cinemaclub.data.repository.CinemaRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CinemaRepositoryImpl
) : ViewModel() {

    val films: MutableStateFlow<BaseModel<List<FilmItem>>?> = MutableStateFlow(null)
    val news: MutableStateFlow<BaseModel<List<NewsItem>>?> = MutableStateFlow(null)
    val filmInfo: MutableStateFlow<BaseModel<FilmItem>?> = MutableStateFlow(null)
    val sessionScheme: MutableStateFlow<BaseModel<SchemeModel>?> = MutableStateFlow(null)

    init {
        getAllFilms()
        getNews()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.GetFilmInfo -> {
                getFilmInfo(event.id)
            }

            is HomeEvent.GetSessionScheme -> {
                getSessionScheme(event.id)
            }
        }
    }

    private fun getFilmInfo(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            filmInfo.update { BaseModel.Loading }

            repository.getFilmInfo(id).also { result ->

                if (result is BaseModel.Success) {
                    filmInfo.update { BaseModel.Success(result.data.filmInfo) }
                } else if (result is BaseModel.Error) {
                    filmInfo.update { BaseModel.Error(result.error) }
                }
            }
        }
    }

    private fun getSessionScheme(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            sessionScheme.update { BaseModel.Loading }

            repository.getSeatsBySession(id).also { result ->

                if (result is BaseModel.Success) {
                    sessionScheme.update { result }
                } else if (result is BaseModel.Error) {
                    sessionScheme.update { BaseModel.Error(result.error) }
                }
            }
        }
    }

    private fun getAllFilms() {

        viewModelScope.launch(Dispatchers.IO) {

            films.update { BaseModel.Loading }

            repository.getListFilms().also { result ->

                if (result is BaseModel.Success) {

                    films.update { BaseModel.Success(result.data.listItem) }

                } else if (result is BaseModel.Error) {
                    Log.d("loadingFilms", result.error)
                }
            }
        }
    }

    private fun getNews() {

        viewModelScope.launch(Dispatchers.IO) {

            news.update { BaseModel.Loading }

            repository.getNews().also { result ->

                if (result is BaseModel.Success) {
                    news.update { BaseModel.Success(result.data.newsList) }
                }
            }
        }
    }
}