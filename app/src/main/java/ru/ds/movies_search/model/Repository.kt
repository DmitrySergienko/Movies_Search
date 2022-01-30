package ru.ds.movies_search.model

//данный репозиторий реализует методы Интерфейса IRepository /
class Repository : IRepository {
    override fun getMovieFromServer(): Film {
        return Film()
    }

    override fun getMovieFromLocalBdWorld(): List<Film> {
        return getWoldM0vies()
    }

    override fun getMovieFromLocalBdRus(): List<Film> {
        return getRusM0vies()
    }

}