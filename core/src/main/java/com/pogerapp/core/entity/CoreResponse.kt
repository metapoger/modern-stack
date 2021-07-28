package com.pogerapp.core.entity

class CoreResponse<T>(val success: T? = null, val error: Throwable? = null){
    fun isSuccess() = success != null && error == null
}