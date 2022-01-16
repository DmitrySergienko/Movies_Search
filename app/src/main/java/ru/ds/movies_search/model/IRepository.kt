package ru.ds.movies_search.model

import ru.ds.movies_search.model.Movie

interface IRepository {
    fun getMovieFromServer(): Movie
}