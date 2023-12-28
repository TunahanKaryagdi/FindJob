package com.tunahankaryagdi.findjob.data.source.local

import kotlinx.coroutines.flow.Flow


interface TokenStore {

    fun getToken() : Flow<String>
    suspend fun saveToken(token: String)
}