package com.tunahankaryagdi.findjob.di

import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService{
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideJobService(retrofit: Retrofit): JobService{
        return retrofit.create(JobService::class.java)
    }
}