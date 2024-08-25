package com.learning.capstone.core.domain.repository

import com.learning.capstone.core.data.Resource
import com.learning.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(tourism: Movie, state: Boolean)

}