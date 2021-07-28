package com.pogerapp.network

import com.pogerapp.core.entity.CoreResponse
import com.pogerapp.core.entity.user.User
import javax.inject.Inject

class RestClientImpl @Inject constructor(
    private val restClient: RestClient
    ):DataRepository {
    override suspend fun obtainData() = handleError {
        restClient.getUsersData()
    }
}