package com.tunahankaryagdi.findjob.di

import android.content.Context
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.data.source.local.TokenStoreImpl
import com.tunahankaryagdi.findjob.data.source.remote.ApplicationService
import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.data.source.remote.SkillService
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    fun provideJobService(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): JobService{
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(JobService::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationService(retrofit: Retrofit): ApplicationService{
        return retrofit.create(ApplicationService::class.java)
    }

    @Provides
    @Singleton
    fun provideSkillService(retrofit: Retrofit): SkillService{
        return retrofit.create(SkillService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenStore(@ApplicationContext context: Context) : TokenStore{
        return TokenStoreImpl(context)
    }
}