package com.pogerapp.core.entity

sealed class State<T>(val success: T? = null, val error: Throwable? = null) {
    class Loading()
    class Success<T>(success: T)
    class Error(error: Throwable)
}