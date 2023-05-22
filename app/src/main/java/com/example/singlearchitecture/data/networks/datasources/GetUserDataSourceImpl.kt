package com.example.singlearchitecture.data.networks.datasources

import com.example.singlearchitecture.data.networks.GithubApi
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import javax.inject.Inject

class GetUserDataSourceImpl @Inject constructor(private val service: GithubApi) :
    GetUserDataSource {
    override suspend fun getDataUsers(
        q: String,
        page: Int,
        sort: String
    ): List<UsersRandomModelItem> {
        return service.getUsers(q = q, page = page, sort = sort).body()?.items ?: emptyList()
    }
}