package com.learning.capstone.core.domain.usecase

import com.learning.capstone.core.domain.model.Movie
import com.learning.capstone.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val tourismRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie() = tourismRepository.getAllMovie()

    override fun getFavoriteMovie() = tourismRepository.getFavoriteMovie()

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) = tourismRepository.setFavoriteMovie(tourism, state)
}