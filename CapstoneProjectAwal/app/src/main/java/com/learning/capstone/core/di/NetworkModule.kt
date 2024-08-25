package com.learning.capstone.core.di

import com.learning.capstone.core.data.source.remote.network.ApiService
import com.learning.capstone.core.data.source.remote.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBearerToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMjUwNGQyNWViYjk3MTJiOGViNTAzMzY5MDUwMGNjYiIsIm5iZiI6MTcyNDU4NjE2Ny4zNjY0ODcsInN1YiI6IjY2Y2IwNjdmOTgzZGM3N2I5NmQzZWIzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.O08UCYWaZ2ptBZZ3Rvg5XLlHQ99Y_Ce3kzmtvFircuo" // Idealnya dari BuildConfig atau sumber aman lainnya
    }

    @Provides
    fun provideAuthInterceptor(bearerToken: String): AuthInterceptor {
        return AuthInterceptor(bearerToken)
    }

    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}