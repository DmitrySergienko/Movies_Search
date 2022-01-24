package ru.ds.movies_search.model

//данный репозиторий реализует методы Интерфейса IRepository /
class Repository : IRepository {
    override fun getMovieFromServer() = Film()

    override fun getMovieFromLocalBdWorld() = getWoldM0vies()

    override fun getMovieFromLocalBdRus() = getRusM0vies()


}