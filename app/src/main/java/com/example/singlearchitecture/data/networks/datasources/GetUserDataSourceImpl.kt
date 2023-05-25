package com.example.singlearchitecture.data.networks.datasources

import android.util.Log
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
        val data = service.getUsers(q = q, page = page, sort = sort)
        Log.e("TAG", data.message() + data.code())
        return  data.body()?.items ?: emptyList()
    }
}