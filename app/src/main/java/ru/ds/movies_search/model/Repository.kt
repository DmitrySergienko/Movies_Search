package ru.ds.movies_search.model

//данный репозиторий реализует методы Интерфейса IRepository /
class Repository : IRepository {
    override fun getMovieFromServer(): Movie = Movie("AnyFilm","AnyType")
}