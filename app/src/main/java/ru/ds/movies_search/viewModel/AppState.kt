package ru.ds.movies_search.viewModel

import ru.ds.movies_search.model.Film


//класс для отображения состояния приложения
sealed class AppState{
    data class Success(val movieData : List<Film>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
