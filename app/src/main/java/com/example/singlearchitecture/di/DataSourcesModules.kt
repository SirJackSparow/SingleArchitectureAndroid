package com.example.singlearchitecture.di

import com.example.singlearchitecture.data.networks.datasources.GetUserDataSource
import com.example.singlearchitecture.data.networks.datasources.GetUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModules {
    @Binds
    @Singleton
    abstract fun getUsersDataSources(getUserDataSourceImpl: GetUserDataSourceImpl): GetUserDataSource
}