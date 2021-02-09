package com.example.chucknorris.network.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.chucknorris.io/"

class AppRetrofit {

    val innerRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }

    inline fun <reified T> generate(): T {
        try {
            return innerRetrofit.create(T::class.java)
        } catch (e: Exception) {
            throw Exception("Not found service ${T::class.java}/ $e")
        }
    }
}