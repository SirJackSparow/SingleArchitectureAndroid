package com.example.singlearchitecture.di

import com.example.singlearchitecture.data.repositories.GetUserRepo
import com.example.singlearchitecture.data.repositories.GetUserRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModules {
    @Singleton
    @Provides
    fun getRepoUsers(getUserRepoImpl: GetUserRepoImpl): GetUserRepo = getUserRepoImpl
}