package com.pogerapp.db

import com.pogerapp.core.entity.user.Department
import com.pogerapp.core.entity.user.User
import com.pogerapp.db.dao.DepartmentDao
import com.pogerapp.db.dao.UserDao
import com.pogerapp.db.models.DepartmentModel
import com.pogerapp.db.models.UserModel
import javax.inject.Inject

class DataStorage @Inject constructor(
    private val userDao: UserDao,
    private val departmentDao: DepartmentDao
) : UserDataStorage,
    DepartmentsDataStorage {

    override suspend fun addUser(user: User) {
        userDao.insert(UserModel.fromEntity(user))
        departmentDao.addAll(user.departments?.map { DepartmentModel.fromEntity(it) })
    }

    override suspend fun addAllUsers(users: List<User>) {
        userDao.insertAll(users)
        users.forEach { user ->
            departmentDao.addAll(user.departments?.map { DepartmentModel.fromEntity(it) })
        }
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getAll().map {
            it.toEntity()
        }
    }

    override suspend fun clearUsers() {
        userDao.clear()
    }

    override suspend fun addAllDepartments(departments: List<Department>) {
        departmentDao.addAll(departments.map { DepartmentModel.fromEntity(it) })
    }

    override suspend fun getDepartments(): List<Department> {
        return departmentDao.getDepartments()?.map {
            it.toEntity()
        } ?: ArrayList()
    }

    override suspend fun clearDepartments() {
        departmentDao.clear()
    }
}
