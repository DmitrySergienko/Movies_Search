package ru.ds.movies_search.viewModel

import ru.ds.movies_search.model.Movie

//класс для отображения состояния приложения
sealed class AppState{
    data class Success(val moviesData: Movie): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}