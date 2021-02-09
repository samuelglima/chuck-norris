package com.example.chucknorris.ui.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.model.Joke
import com.example.chucknorris.network.repository.ChuckNorrisRepository
import com.example.chucknorris.network.service.read
import com.example.chucknorris.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel(var chuckNorrisRepository: ChuckNorrisRepository) : ViewModel() {

    private val _loading = MutableLiveData<Status>()
    val loading: LiveData<Status> get() = _loading

    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> get() = _joke


    fun get(category: String) = viewModelScope.launch {
        _loading.value = Status.LOADING

        withContext(Dispatchers.IO) {
            chuckNorrisRepository.getRadom(category)
        }.read({ response ->
            response?.let {
                _joke.value = response
                _loading.value = Status.SUCCESS
            }
        }, {
            _loading.value = Status.ERROR
        })
    }

}