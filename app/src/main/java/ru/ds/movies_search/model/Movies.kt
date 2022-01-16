package ru.ds.movies_search.model

sealed class Movies{
    data class MoviesType(var type: String, var duration : Int) : Movies()
    data class MoviesName(var name: String, var typeM : String) : Movies()
}
