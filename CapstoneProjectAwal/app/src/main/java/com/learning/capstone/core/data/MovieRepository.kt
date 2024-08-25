package com.learning.capstone.core.data

import com.learning.capstone.core.data.source.local.LocalDataSource
import com.learning.capstone.core.data.source.remote.RemoteDataSource
import com.learning.capstone.core.data.source.remote.network.ApiResponse
import com.learning.capstone.core.data.source.remote.response.ListMovieItem
import com.learning.capstone.core.domain.model.Movie
import com.learning.capstone.core.domain.repository.IMovieRepository
import com.learning.capstone.core.utils.AppExecutors
import com.learning.capstone.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ListMovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ListMovieItem>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ListMovieItem>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(tourismList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(tourism: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourismEntity, state) }
    }
}
