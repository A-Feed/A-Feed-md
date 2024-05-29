package com.capstone.afeed.data

sealed class ResponseState<out R> private constructor() {

    data class Success<out T>(val data: T) : ResponseState<T>()

    data class Error(val error: String) : ResponseState<Nothing>()

    data object Loading : ResponseState<Nothing>()
}
