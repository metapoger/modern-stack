package com.pogerapp.departments_list

import android.app.Application
import android.arch.lifecycle.SingleLiveEvent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.pogerapp.core.entity.State
import com.pogerapp.core.entity.user.Department
import kotlinx.coroutines.launch
import javax.inject.Inject

class DepartmentsListViewModel @ViewModelInject constructor(
    private val app: Application,
    private val getDepartmentsListUseCase: GetDepartmentsListUseCase
) : AndroidViewModel(app) {
    val departmentsListLiveData = SingleLiveEvent<State<List<Department>>>()

    fun getDepartmentsList() {
        viewModelScope.launch {
            departmentsListLiveData.value = State.Loading()
            val result = getDepartmentsListUseCase()
            if(result.isSuccess()){
                State.Success(result.success)
            }else{
                State.Error(result.error)
            }
        }
    }
}