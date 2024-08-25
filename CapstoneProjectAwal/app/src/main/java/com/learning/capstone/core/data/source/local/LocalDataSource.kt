package com.learning.capstone.core.data.source.local

import com.learning.capstone.core.data.source.local.entity.MovieEntity
import com.learning.capstone.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(tourismList: List<MovieEntity>) = movieDao.insertMovie(tourismList)

    fun setFavoriteMovie(tourism: MovieEntity, newState: Boolean) {
        tourism.isFavorite = newState
        movieDao.updateFavoriteMovie(tourism)
    }
}