package com.pieter.party.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.recyclerview.widget.RecyclerView
import com.pieter.party.R
import com.pieter.party.adapter.RecyclerAdapter
import com.pieter.party.datastore.CustomSerializer
import com.pieter.party.datastore.EntryItem
import com.pieter.party.model.DatastoreEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class DatastoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datastore)

        val recyclerAdapter = RecyclerAdapter()

        val mainList = findViewById<RecyclerView>(R.id.mainList)

        mainList.apply {
            adapter = recyclerAdapter
        }

        val dataStore: DataStore<EntryItem> =
            createDataStore(
                fileName = "entry_items.pb",
                serializer = CustomSerializer
            )

        val userPreferencesFlow: Flow<EntryItem> = dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    Log.e("datastore", exception.toString())
                    emit(EntryItem.getDefaultInstance())
                } else {
                    throw exception
                }
            }

        recyclerAdapter.setListItems(
            arrayListOf(
                DatastoreEntry("1", 1),
                DatastoreEntry("2", 2),
                DatastoreEntry("3", 3),
                DatastoreEntry("4", 4),
                DatastoreEntry("5", 5),
                DatastoreEntry("6", 6),
                DatastoreEntry("7", 7),
                DatastoreEntry("8", 8),
                DatastoreEntry("9", 9),
                DatastoreEntry("10", 10)
            )
        )
    }
}