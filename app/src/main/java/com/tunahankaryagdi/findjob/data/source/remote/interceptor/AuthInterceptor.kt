package com.tunahankaryagdi.findjob.data.source.remote.interceptor

import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenStore: TokenStore
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val jwt = runBlocking { getJwt() }

        request.addHeader("Authorization", "Bearer $jwt")

        return chain.proceed(request.build())
    }

    private suspend fun getJwt() : String{
        return tokenStore.getToken().first()
    }

}