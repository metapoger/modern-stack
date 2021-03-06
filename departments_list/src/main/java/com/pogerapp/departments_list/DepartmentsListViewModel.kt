package com.pogerapp.departments_list

import android.app.Application
import android.arch.lifecycle.SingleLiveEvent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pogerapp.core.entity.State
import com.pogerapp.core.entity.user.Specialty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentsListViewModel @Inject constructor(
    private val app: Application,
    private val getDepartmentsListUseCase: GetDepartmentsListUseCase
) : AndroidViewModel(app) {
    val departmentsListLiveData = SingleLiveEvent<State<List<Specialty>>>()

    fun getDepartmentsList() {
        viewModelScope.launch {
            departmentsListLiveData.value = State.Loading()
            val result = getDepartmentsListUseCase()
            departmentsListLiveData.value = if(result.isSuccess()){
                State.Success(result.success!!)
            }else{
                State.Error(result.error)
            }
        }
    }
}