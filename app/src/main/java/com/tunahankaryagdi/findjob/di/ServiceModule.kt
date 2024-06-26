package com.tunahankaryagdi.findjob.di

import android.content.Context
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.data.source.local.TokenStoreImpl
import com.tunahankaryagdi.findjob.data.source.remote.ApplicationService
import com.tunahankaryagdi.findjob.data.source.remote.CompanyService
import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.data.source.remote.PreferredLocationService
import com.tunahankaryagdi.findjob.data.source.remote.RecommendedService
import com.tunahankaryagdi.findjob.data.source.remote.SkillService
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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

    @Provides
    @Singleton
    fun provideCompanyService(retrofit: Retrofit): CompanyService{
        return retrofit.create(CompanyService::class.java)
    }

    @Provides
    @Singleton
    fun providePreferredLocationService(retrofit: Retrofit): PreferredLocationService{
        return retrofit.create(PreferredLocationService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecommendedService(retrofit: Retrofit): RecommendedService{
        return retrofit.create(RecommendedService::class.java)
    }
}