package com.nabila.userpjs.data.repository

import com.nabila.userpjs.R
import com.nabila.userpjs.data.remote.api.ApiService
import com.nabila.userpjs.data.remote.model.UsersItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(
    private val apiService: ApiService
) : UserRepository {

    // get users
    override fun getUsers(): Flow<ResultState<List<UsersItem>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getUsers()
            emit(ResultState.Success(response.users))
        } catch (e: Exception) {
            val errorMessageId = when (e) {
                is IOException -> R.string.network_error_message
                is HttpException -> R.string.server_error_message
                else -> R.string.unknown_error_message
            }
            emit(ResultState.Error(errorMessageId))
        }
    }

    // get detail user
    override fun getUserById(id: Int): Flow<ResultState<UsersItem>> = flow {
        emit(ResultState.Loading)
        try {
            val user = apiService.getUserById(id)
            emit(ResultState.Success(user))
        } catch (e: Exception) {
            val errorMessageId = when (e) {
                is IOException -> R.string.network_error_message
                is HttpException -> R.string.server_error_message
                else -> R.string.unknown_error_message
            }
            emit(ResultState.Error(errorMessageId))
        }
    }

    // search
    override fun searchUsers(query: String): Flow<ResultState<List<UsersItem>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getUsers()
            val filtered = response.users.filter { user ->
                "${user.firstName} ${user.lastName}".contains(query, ignoreCase = true)
            }
            if (filtered.isEmpty()) {
                emit(ResultState.Error(R.string.unknown_user))
            } else {
                emit(ResultState.Success(filtered))
            }
        } catch (e: Exception) {
            val errorMessageId = when (e) {
                is IOException -> R.string.network_error_message
                is HttpException -> R.string.server_error_message
                else -> R.string.unknown_error_message
            }
            emit(ResultState.Error(errorMessageId))
        }
    }

}