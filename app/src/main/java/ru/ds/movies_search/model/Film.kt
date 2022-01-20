package ru.ds.movies_search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(

    val movie: Movie = getDefaultMovie(),
    val type: String = "cartoons",
    val duration: Int = 100

) : Parcelable

fun getDefaultMovie() = Movie ("AnyFilm","cartoons",100)

fun getWoldM0vies(): List<Film>{
    return listOf(
        Film(Movie("Storm","cartoons",90)),
        Film(Movie("Storm1","horror",90)),
        Film(Movie("Storm2","comedy",100)),
        Film(Movie("Storm3","cartoons",100))

    )
}
fun getRusM0vies(): List<Film>{
    return listOf(
        Film(Movie("Planet0","cartoons",90)),
        Film(Movie("Planet1","horror",90)),
        Film(Movie("Planet2","comedy",100)),
        Film(Movie("Planet3","cartoons",100))

    )
}



