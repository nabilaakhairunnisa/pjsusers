package com.nabila.userpjs.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabila.userpjs.data.remote.model.UsersItem
import com.nabila.userpjs.data.repository.ResultState
import com.nabila.userpjs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository): ViewModel() {

    private val _userState = MutableStateFlow<ResultState<List<UsersItem>>>(ResultState.Loading)
    val userState: StateFlow<ResultState<List<UsersItem>>> = _userState

    private var originalList: List<UsersItem> = emptyList()

    var searchQuery = mutableStateOf("")
        private set

    init {
        getUsers()
    }

    // list user
    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers().collect { result ->
                if (result is ResultState.Success) {
                    // save original data
                    originalList = result.data
                }
                _userState.value = result
            }
        }
    }

    // search
    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
        viewModelScope.launch {
            repository.searchUsers(query).collect { result ->
                if (result is ResultState.Success) {
                    originalList = result.data
                }
                _userState.value = result
            }
        }
    }

    // sort
    fun sortByName(ascending: Boolean) {
        val sorted = if (ascending) {
            originalList.sortedBy { "${it.firstName} ${it.lastName}" }
        } else {
            originalList.sortedByDescending { "${it.firstName} ${it.lastName}" }
        }
        _userState.value = ResultState.Success(sorted)
    }

}