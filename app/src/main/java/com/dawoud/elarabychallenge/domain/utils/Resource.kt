package com.dawoud.domain.utils

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val exception: String , val data : T? = null) : Resource<T>()
    object Loading : Resource<Nothing>()
}

