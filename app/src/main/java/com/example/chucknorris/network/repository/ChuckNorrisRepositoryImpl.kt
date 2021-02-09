package com.example.chucknorris.network.repository

import com.example.chucknorris.model.Joke
import com.example.chucknorris.network.repository.remote.ChuckNorrisApi
import com.example.chucknorris.network.service.ResponseAny
import com.example.chucknorris.network.service.safeApiCall

class ChuckNorrisRepositoryImpl(val api: ChuckNorrisApi): ChuckNorrisRepository {

    override suspend fun getRadom(category: String) = safeApiCall { api.getRandom(category) }

    override suspend fun getCategories() = safeApiCall { api.getCategories() }
}