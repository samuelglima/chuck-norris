package com.example.chucknorris.network.service

import android.util.Log
import com.google.gson.stream.MalformedJsonException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ResponseAny<T> {
    companion object {
        fun <T> create(response: Response<T>): ResponseAny<T> {
            return if (response.isSuccessful) {
                val body = response.body()

                if (body == null || response.code() == 204) {
                    ResponseError("Empty data!")

                } else {
                    ResponseSuccess(body)
                }

            } else {
                val responseError =
                    "Error code: ${response.code()} - Error body: ${response.errorBody()}"
                Log.e("ResponseAny", responseError)
                ResponseError(createOnFailure(response))
            }
        }

        fun <T> create(error: Throwable): ResponseError<T> = ResponseError(createOnFailure(error))
    }
}

data class ResponseError<T>(var msgError: String? = null) : ResponseAny<T>()

class ResponseSuccess<T>(val body: T) : ResponseAny<T>()

private fun <T> createOnFailure(response: Response<T>): String {
    return when {
        response.code() == 500 -> "Ocorreu um problema inesperado, tente novamente mais tarde."
        else -> ""
    }
}

fun createOnFailure(t: Throwable): String? {
    Log.e("ResponseAny", "Exception: $t")
    return when (t) {
        is UnknownHostException -> {
            "Você está sem conexão com a internet."
        }
        is MalformedJsonException -> {
            "Algo aconteceu e não conseguimos ler os dados corretamente."
        }
        is SocketTimeoutException -> {
            "O servidor demorou para responder. Tente novamente mais tarde."
        }
        else -> {
            "Ocorreu um problema, tente novamente."
        }
    }
}