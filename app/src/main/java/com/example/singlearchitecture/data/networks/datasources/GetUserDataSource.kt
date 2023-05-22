package com.example.singlearchitecture.data.networks.datasources

import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem

interface GetUserDataSource {
    suspend fun getDataUsers(q: String, page: Int , sort: String) : List<UsersRandomModelItem>
}