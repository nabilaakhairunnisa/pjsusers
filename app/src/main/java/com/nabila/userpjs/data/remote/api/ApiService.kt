package com.nabila.userpjs.data.remote.api

import com.nabila.userpjs.data.remote.model.ResponseUser
import com.nabila.userpjs.data.remote.model.UsersItem
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("users")
    suspend fun getUsers(): ResponseUser

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UsersItem

}