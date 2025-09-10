package com.nabila.userpjs.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabila.userpjs.data.remote.model.UsersItem
import com.nabila.userpjs.data.repository.ResultState
import com.nabila.userpjs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<ResultState<UsersItem>>(ResultState.Loading)
    val userState: StateFlow<ResultState<UsersItem>> = _userState

    fun getUserById(id: Int) {
        viewModelScope.launch {
            repository.getUserById(id).collect { result ->
                _userState.value = result
            }
        }
    }
}
