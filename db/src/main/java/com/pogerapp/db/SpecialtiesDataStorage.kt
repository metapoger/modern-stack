package com.pogerapp.db


import com.pogerapp.core.entity.user.Department

interface DepartmentsDataStorage {
    suspend fun addAllDepartments(departments: List<Department>)
    suspend fun getDepartments(): List<Department>
    suspend fun clearDepartments()
}