package com.pogerapp.network

import com.pogerapp.core.entity.CoreResponse
import retrofit2.Response
import java.lang.NullPointerException

suspend fun <T> handleError(block: suspend () -> Response<T>): CoreResponse<T> {
    return try {
        val result = block()
        if(result.isSuccessful && result.body() != null){
            CoreResponse(result.body())
        }else{
            CoreResponse(error = NullPointerException("Couldn't get response"))
        }
    } catch (ex: Exception) {
        CoreResponse(error = ex)
    }
}