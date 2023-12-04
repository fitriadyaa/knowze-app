package com.knowzeteam.knowze.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val idToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer $idToken")
            .build()
        return chain.proceed(newRequest)
    }
}
