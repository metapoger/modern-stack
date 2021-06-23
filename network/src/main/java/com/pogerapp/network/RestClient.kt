package com.pogerapp.network

import com.pogerapp.core.entity.NetworkResponse
import com.pogerapp.core.entity.user.User
import retrofit2.Response
import retrofit2.http.GET

interface RestClient {
    @GET("data.json")
    suspend fun getUsersData(): Response<NetworkResponse<ArrayList<User>>>
}