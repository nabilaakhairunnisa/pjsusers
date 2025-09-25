package com.nabila.userpjs.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabila.userpjs.R
import com.nabila.userpjs.data.remote.model.UsersItem
import com.nabila.userpjs.data.repository.ResultState
import com.nabila.userpjs.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository): ViewModel() {

    private val _userState = MutableStateFlow<ResultState<List<UsersItem>>>(ResultState.Loading)
    val userState: StateFlow<ResultState<List<UsersItem>>> = _userState

    private val originalList = MutableStateFlow<List<UsersItem>>(emptyList())

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    init {
        getUsers()
    }

    // list user
    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers().collect { result ->
                if (result is ResultState.Success) {
                    // save original data
                    originalList.value = result.data
                }
                _userState.value = result
            }
        }
    }

    // save typed text
    fun search(query: String) {
        _search.value = query
        searchByName(query)
    }

    private fun searchByName(query: String) {
        // if search blank, show all users
        if (query.isBlank()) {
            _userState.value = ResultState.Success(originalList.value)
            return
        }

        // filter full name by query
        val filteredUsers = originalList.value.filter { user ->
            "${user.firstName} ${user.lastName}".contains(query, ignoreCase = true)
                    || user.company.title.contains(query, ignoreCase = true)
        }

        if (filteredUsers.isEmpty()) {
            _userState.value = ResultState.Error(R.string.unknown_user)
        } else {
            _userState.value = ResultState.Success(filteredUsers)
        }
    }

    // sort
    fun sortByName(ascending: Boolean) {
        val sorted = if (ascending) {
            originalList.value.sortedBy { "${it.firstName} ${it.lastName}" }
        } else {
            originalList.value.sortedByDescending { "${it.firstName} ${it.lastName}" }
        }
        _userState.value = ResultState.Success(sorted)
    }

    // sort
    fun sortByAge(ascending: Boolean) {
        val sorted = if (ascending) {
            originalList.value.sortedBy { user ->
                user.age
            }
        } else {
            originalList.value.sortedByDescending { user ->
                user.age
            }
        }
        _userState.value = ResultState.Success(sorted)
    }

}