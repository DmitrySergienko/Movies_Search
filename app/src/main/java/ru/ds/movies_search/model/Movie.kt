package ru.ds.movies_search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val type: String,
    val duration: Int
): Parcelable
