package com.tunahankaryagdi.findjob.di

import com.tunahankaryagdi.findjob.data.repository.JobRepositoryImpl
import com.tunahankaryagdi.findjob.data.repository.UserRepositoryImpl
import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
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
    fun provideUserRepository(userService: UserService) : UserRepository{
        return UserRepositoryImpl(userService)
    }


    @Provides
    @Singleton
    fun provideJobRepository(jobService: JobService) : JobRepository{
        return JobRepositoryImpl(jobService)
    }
}