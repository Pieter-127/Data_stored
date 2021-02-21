package com.pieter.party.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pieter.party.repo.DataRepository
import kotlinx.coroutines.launch

class DatastoreViewModel(dataRepository: DataRepository) : ViewModel() {

    val flow = dataRepository.datastoreFlow.asLiveData()

    init {
        viewModelScope.launch {
            dataRepository.updateDatastore()
        }
    }

    class DatastoreViewModelFactory(
        private val repository: DataRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DatastoreViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DatastoreViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}