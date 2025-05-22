package com.example.peekstore.data.service

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object TokenManager {

    private val UID_KEY = stringPreferencesKey("user_uid")

    fun getUserUid(context: Context): Flow<String?>{
        return context.dataStore.data.map { preferences ->
            preferences[UID_KEY]
        }
    }

    suspend fun saveUserUid(context: Context,uid: String){
        context.dataStore.edit { preferences ->
            preferences[UID_KEY] = uid
        }
    }

    suspend fun clearUserUid(context: Context){
        context.dataStore.edit { preferences ->
            preferences.remove(UID_KEY)
        }
    }
}