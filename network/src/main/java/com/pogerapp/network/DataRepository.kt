package com.pogerapp.network

import com.pogerapp.core.entity.CoreResponse
import com.pogerapp.core.entity.user.User

interface DataRepository {
    suspend fun obtainData(): CoreResponse<ArrayList<User>>
}