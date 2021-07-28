package com.pogerapp.core.entity

sealed class State<T>(val success: T? = null, val error: Throwable? = null) {
    class Loading<T>() : State<T>()
    class Success<T>(success: T) : State<T>(success, null)
    class Error<T>(error: Throwable?) : State<T>(null, error)
}