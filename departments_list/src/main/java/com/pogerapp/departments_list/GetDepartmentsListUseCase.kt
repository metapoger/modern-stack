package com.pogerapp.departments_list

import com.pogerapp.core.entity.CoreResponse
import com.pogerapp.db.DataStorageRepository
import com.pogerapp.network.DataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDepartmentsListUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    private val dataStorageRepository: DataStorageRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke()  = withContext(dispatcher){
        val result = dataRepository.obtainData()
        dataStorageRepository.clearAll()
        if(result.isSuccess()){
            dataStorageRepository.addAllUsers(result.success?:ArrayList())
        }
        CoreResponse(dataStorageRepository.getDepartments(), result.error)
    }
}