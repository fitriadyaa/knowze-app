package com.knowzeteam.knowze.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val newRequestBuilder = chain.request().newBuilder()
        if (!token.isNullOrEmpty()) {
            newRequestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(newRequestBuilder.build())
    }
}

