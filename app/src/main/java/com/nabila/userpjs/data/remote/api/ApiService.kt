package com.nabila.userpjs.data.remote.api

import com.nabila.userpjs.data.remote.model.ResponseUser
import com.nabila.userpjs.data.remote.model.UsersItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("users")
    suspend fun getUsers(): ResponseUser

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UsersItem

    @GET("users/search")
    suspend fun searchUsers(@Query("q") query: String): ResponseUser

    // sort
    @GET("users/search")
    suspend fun sortUsers(
        @Query("sortBy") sortBy: String = "firstName",
        @Query("order") orderBy: String
    ): ResponseUser
}