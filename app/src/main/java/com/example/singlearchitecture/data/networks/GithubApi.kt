package com.example.singlearchitecture.data.networks

import com.example.singlearchitecture.data.networks.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    suspend fun getUsers(
        @Query("q") q: String = "super",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("sort") sort: String,
    ): Response<UsersRandomModel>

    @GET("/users/{name}")
    suspend fun detailUser(
        @Path("name") name: String
    ): Response<DetailModel>
}