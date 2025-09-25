package com.nabila.userpjs.data.repository

import com.nabila.userpjs.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading)
    try {
        val response = apiCall()
        emit(ResultState.Success(response))
    } catch (e: Exception) {
        val errorMessageId = when (e) {
            is IOException -> R.string.network_error_message
            is HttpException -> R.string.server_error_message
            else -> R.string.unknown_error_message
        }
        emit(ResultState.Error(errorMessageId))
    }
}