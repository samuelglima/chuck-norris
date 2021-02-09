package com.example.chucknorris.network.service

fun <T>ResponseAny<T>.read(success: (T) -> Unit, error: ((String?) -> Unit)? = null){
    when(this){
        is ResponseSuccess ->  success(this.body)
        is ResponseError -> error?.invoke(this.msgError)
    }
}