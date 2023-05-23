package com.example.singlearchitecture.data.repositories

import com.example.singlearchitecture.data.networks.datasources.GetUserDataSource
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetUserRepoImpl @Inject constructor(private val dataSource: GetUserDataSource) : GetUserRepo {
    override suspend fun getDataUsers(
        q: String,
        page: Int,
        sort: String
    ): Flow<Resource<List<UsersRandomModelItem>>> = flow {
        val result = dataSource.getDataUsers(q = q, page = page, sort = sort)
        if (result.isNotEmpty()) {
            emit(Resource.Success(data = result))
        } else {
            emit(Resource.Error(message = ""))
        }
    }
}
