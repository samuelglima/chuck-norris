package com.example.chucknorris.di.modules

import com.example.chucknorris.network.repository.ChuckNorrisRepository
import com.example.chucknorris.network.repository.ChuckNorrisRepositoryImpl
import com.example.chucknorris.network.repository.remote.ChuckNorrisApi
import com.example.chucknorris.network.retrofit.AppRetrofit
import com.example.chucknorris.ui.home.HomeViewModel
import com.example.chucknorris.ui.joke.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object appModules {

    fun allModules() = listOf(viewModel, repository)

    private val viewModel = module {
        viewModel { HomeViewModel(get()) }
        viewModel { JokeViewModel(get()) }
    }

    private val repository = module {
        single { ChuckNorrisRepositoryImpl(get()) as ChuckNorrisRepository }
        single { AppRetrofit().generate() as ChuckNorrisApi }
    }
}