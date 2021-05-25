package com.catly.quickapp.data

import com.catly.quickapp.data.model.RepositoryListItem
import retrofit2.http.GET

interface WebService {

    @GET("repos")
    suspend fun getRepoList(): List<RepositoryListItem>
}