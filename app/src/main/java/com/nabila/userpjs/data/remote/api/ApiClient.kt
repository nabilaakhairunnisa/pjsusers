package com.nabila.userpjs.data.remote.api

import com.nabila.userpjs.data.repository.UserRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.nabila.userpjs.data.repository.UserRepositoryImpl
import com.nabila.userpjs.ui.detail.DetailViewModel
import com.nabila.userpjs.ui.main.MainViewModel

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<UserRepository> { UserRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }


}