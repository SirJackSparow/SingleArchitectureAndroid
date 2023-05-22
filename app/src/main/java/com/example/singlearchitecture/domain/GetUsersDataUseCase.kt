package com.example.singlearchitecture.domain

import com.example.singlearchitecture.data.repositories.GetUserRepo
import javax.inject.Inject

class GetUsersDataUseCase @Inject constructor(private val repo: GetUserRepo) {
    suspend fun invoke(q: String, page: Int, sort: String) =
        repo.getDataUsers(q = q, page = page, sort = sort)
}