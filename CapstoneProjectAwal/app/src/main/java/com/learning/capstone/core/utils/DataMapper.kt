package com.learning.capstone.core.utils

import com.learning.capstone.core.data.source.local.entity.MovieEntity
import com.learning.capstone.core.data.source.remote.response.ListMovieItem
import com.learning.capstone.core.data.source.remote.response.MovieResponse
import com.learning.capstone.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<ListMovieItem>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                description = it.overview,
                title = it.title,
                release_date = it.releaseDate,
                poster_path = it.posterPath,
                isFavorite = false
            )
            tourismList.add(movie)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                description = it.description,
                title = it.title,
                release_date = it.release_date,
                poster_path = it.poster_path,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        description = input.description,
        title = input.title,
        release_date = input.release_date,
        poster_path = input.poster_path,
        isFavorite = input.isFavorite
    )
}