package com.example.singlearchitecture.data.repositories

import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserRepo {
    suspend fun getDataUsers(
        q: String,
        page: Int,
        sort: String
    ): Flow<Resource<List<UsersRandomModelItem>>>
}
