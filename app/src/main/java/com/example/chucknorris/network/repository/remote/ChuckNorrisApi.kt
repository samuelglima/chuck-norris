package com.example.chucknorris.network.repository.remote

import com.example.chucknorris.model.Joke
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ChuckNorrisApi {

    @GET("jokes/random")
    suspend fun getRandom(
        @Query("category") category: String): Response<Joke>

    @GET("jokes/categories")
    suspend fun getCategories(): Response<ArrayList<String>>


}