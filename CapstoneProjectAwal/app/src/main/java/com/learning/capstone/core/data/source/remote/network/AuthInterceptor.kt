package com.learning.capstone.core.data.source.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val bearerToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $bearerToken")
            .header("accept", "application/json")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}