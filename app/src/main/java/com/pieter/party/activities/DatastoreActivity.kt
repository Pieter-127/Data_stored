package com.pieter.party.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pieter.party.R
import com.pieter.party.adapter.RecyclerAdapter
import com.pieter.party.repo.DataRepository
import com.pieter.party.viewmodel.DatastoreViewModel

class DatastoreActivity : AppCompatActivity() {

    lateinit var viewModel: DatastoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datastore)

        val recyclerAdapter = RecyclerAdapter()

        val mainList = findViewById<RecyclerView>(R.id.mainList)

        mainList.apply {
            adapter = recyclerAdapter
        }

        viewModel = ViewModelProvider(
            this,
            DatastoreViewModel.DatastoreViewModelFactory(DataRepository(this))
        ).get(DatastoreViewModel::class.java)

        viewModel.flow.observe(this, Observer {

            it?.let { entryItem ->
                Log.d("this", entryItem.string)
                Log.d("this", entryItem.int.toString())

                recyclerAdapter.setListItems(arrayListOf(entryItem, entryItem, entryItem))
            }
        })
    }
}