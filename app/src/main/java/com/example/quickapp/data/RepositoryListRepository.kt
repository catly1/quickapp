package com.example.quickapp.data

import com.example.quickapp.data.model.RepositoryListItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryListRepository() {
    private val webService: WebService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/users/QuickenLoans/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        webService = retrofit.create(WebService::class.java)
    }

    suspend fun getRepositories(): List<RepositoryListItem> {
        return webService.getRepoList()
    }
}