package com.example.chucknorris.network.repository

import com.example.chucknorris.model.Joke
import com.example.chucknorris.network.service.ResponseAny

interface ChuckNorrisRepository {

    suspend fun getRadom(category: String): ResponseAny<Joke>

    suspend fun getCategories(): ResponseAny<ArrayList<String>>
}