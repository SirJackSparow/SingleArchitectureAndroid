package com.example.singlearchitecture.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel(
    @PrimaryKey
    val id: Int,
    val login: String,
    val score: Double,
    val type: String,
    val url: String,
    val favourites: Boolean
)