package com.pieter.party.repo

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import com.pieter.party.datastore.CustomSerializer
import com.pieter.party.datastore.EntryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class DataRepository(context: Context) {

    private val dataStore: DataStore<EntryItem> = context.createDataStore(
        fileName = "entry_items.pb",
        serializer = CustomSerializer
    )

    val datastoreFlow: Flow<EntryItem> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e("datastore", exception.toString())
                emit(EntryItem.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateDatastore() {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setInt(3).setString("hello").build()
        }
    }

}