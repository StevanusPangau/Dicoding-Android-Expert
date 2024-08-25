package com.learning.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var movieId: Int,
    var title: String,
    var description: String,
    var release_date: String,
    var poster_path: String,
    var isFavorite: Boolean
) : Parcelable