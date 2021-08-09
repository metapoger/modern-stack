package com.pogerapp.network

import com.pogerapp.core.entity.CoreResponse
import com.pogerapp.core.entity.NetworkResponse
import retrofit2.Response
import java.lang.NullPointerException

suspend fun <T> handleError(block: suspend () -> Response<NetworkResponse<T>>): CoreResponse<T> {
    return try {
        val result = block()
        if(result.isSuccessful && result.body() != null && result.body()?.data != null){
            CoreResponse(result.body()?.data)
        }else{
            CoreResponse(error = NullPointerException("Couldn't get response"))
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        CoreResponse(error = ex)
    }
}