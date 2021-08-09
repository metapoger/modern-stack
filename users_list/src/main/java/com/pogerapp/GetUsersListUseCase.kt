package com.pogerapp

import com.pogerapp.core.entity.CoreResponse
import com.pogerapp.db.DataStorage
import com.pogerapp.db.DataStorageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val dataStorageRepository: DataStorageRepository
) {
    suspend operator fun invoke(departmentId: Int) = withContext(dispatcher) {
        try {
            CoreResponse(dataStorageRepository.getUsersByDepartment(departmentId), null)
        } catch (ex: Exception) {
            CoreResponse(null, ex)
        }
    }
}