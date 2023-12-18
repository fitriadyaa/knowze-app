package com.knowzeteam.knowze.data.remote.retrofit

import android.content.Context
import com.knowzeteam.knowze.data.remote.AuthInterceptor
import com.knowzeteam.knowze.utils.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConfig {
    fun getApiService(context: Context): ApiService {
        val token = Preference.getToken(context)

        val okHttpClient = OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addInterceptor(loggingInterceptor)
            token?.let { addInterceptor(AuthInterceptor { Preference.getToken(context) })
            }
        }.build()

        return Retrofit.Builder()
            .baseUrl("http://34.122.11.96:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}
