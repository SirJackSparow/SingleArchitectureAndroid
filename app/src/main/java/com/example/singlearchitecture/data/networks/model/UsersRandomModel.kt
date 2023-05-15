package com.example.singlearchitecture.data.networks.model

data class UsersRandomModel(
    val incomplete_results: Boolean,
    val items: List<UsersRandomModelItem>,
    val total_count: Int
)