package com.tunahankaryagdi.findjob.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenStoreImpl(private val context: Context) : TokenStore {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }


    override fun getToken() : Flow<String>{
        return context.dataStore.data.map {preferences->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
}