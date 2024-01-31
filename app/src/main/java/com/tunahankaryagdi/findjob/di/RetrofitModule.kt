package com.tunahankaryagdi.findjob.di

import com.tunahankaryagdi.findjob.data.source.remote.interceptor.AuthInterceptor
import com.tunahankaryagdi.findjob.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder() : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttp(tokenInterceptor: AuthInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
    }

}