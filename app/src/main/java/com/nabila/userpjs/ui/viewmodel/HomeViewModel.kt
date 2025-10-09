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

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    init {
        getUsers()
    }

    // list user
    fun getUsers() {
        viewModelScope.launch {
            repository.getUsers().collect { result ->
                _userState.value = result
            }
        }
    }

    // search users
    private fun searchUsers(query: String) {
        viewModelScope.launch {
            repository.searchUsers(query).collect { result ->
                _userState.value = result
            }
        }
    }

    // save typed text
    fun search(query: String) {
        _search.value = query
        searchUsers(query)
    }

    // sort
    fun sortByName(ascending: Boolean) {
        viewModelScope.launch {
            val orderBy = if (ascending) "asc" else "desc"
            repository.sortUsers(orderBy = orderBy).collect { sortedUsers ->
                _userState.value = sortedUsers
            }
        }
    }
}