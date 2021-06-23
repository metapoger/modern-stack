package com.pogerapp.network

import javax.inject.Inject

class RestClientImpl @Inject constructor(private val restClient: RestClient) {
    suspend fun getUsers() = handleError {
        restClient.getUsersData()
    }
}