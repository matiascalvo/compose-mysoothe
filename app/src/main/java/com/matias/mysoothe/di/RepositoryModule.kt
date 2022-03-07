package com.matias.mysoothe.di

import com.matias.mysoothe.data.repositories.RecordsRepositoryImpl
import com.matias.mysoothe.data.repositories.UserRepositoryImpl
import com.matias.mysoothe.domain.repositories.RecordsRepository
import com.matias.mysoothe.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun userRepositoryProvider() = UserRepositoryImpl() as UserRepository

    @Provides
    @Singleton
    fun recordsRepositoryProvider() = RecordsRepositoryImpl() as RecordsRepository
}
