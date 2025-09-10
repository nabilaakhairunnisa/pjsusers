package com.nabila.userpjs.data.repository

import com.nabila.userpjs.data.remote.model.UsersItem
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<ResultState<List<UsersItem>>>
    fun getUserById(id: Int): Flow<ResultState<UsersItem>>
    fun searchUsers(query: String): Flow<ResultState<List<UsersItem>>>
}