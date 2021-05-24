package com.example.quickapp.data

import com.example.quickapp.data.model.RepositoryListItem
import retrofit2.http.GET

interface WebService {

    @GET("repos")
    suspend fun getRepoList(): List<RepositoryListItem>
}