package com.example.singlearchitecture.data.repositories

import com.example.singlearchitecture.data.networks.GithubApi
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetUserRepoImpl @Inject constructor(private val service: GithubApi) : GetUserRepo {
    override suspend fun getDataUsers(
        q: String,
        page: Int,
        sort: String
    ): Flow<Resource<List<UsersRandomModelItem>>> = flow {
        val result = service.getUsers(q = q, page = page, sort = sort).body()

    }
}