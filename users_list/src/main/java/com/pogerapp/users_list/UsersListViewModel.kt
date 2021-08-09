package com.pogerapp.users_list

import android.app.Application
import android.arch.lifecycle.SingleLiveEvent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pogerapp.GetUsersListUseCase
import com.pogerapp.core.entity.State
import com.pogerapp.core.entity.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val app: Application,
    private val getUsersListUseCase: GetUsersListUseCase
): AndroidViewModel(app) {

    val usersLiveData = SingleLiveEvent<State<List<User>>>()

    fun getUsers(departmentId: Int){
        usersLiveData.value = State.Loading()
        viewModelScope.launch {
            val result = getUsersListUseCase(departmentId)
            usersLiveData.value = if(result.isSuccess()){
                State.Success(result.success!!)
            }else{
                State.Error(result.error)
            }
        }
    }

}