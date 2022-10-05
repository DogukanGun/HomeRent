package com.dag.homerent.localdatastorage.preferencesdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataStore @Inject constructor(
    @ApplicationContext var context: Context,
) {

    companion object {
        val FIRST_LOGIN = booleanPreferencesKey("firs_login")
    }

    var loggedInScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val USER_PREFERENCES_NAME = "preferencesStore"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = USER_PREFERENCES_NAME,
        scope = loggedInScope
    )

    fun <T> read(key: Preferences.Key<T>) = context.dataStore.data.map { preferences ->
        preferences[key]
    }

    suspend fun <T> write(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}