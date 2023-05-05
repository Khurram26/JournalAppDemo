package com.example.demotest.ui

import androidx.lifecycle.ViewModel
import com.example.demotest.model.JournalEntryEntity
import com.example.demotest.room.JournalEntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddJournalViewModel @Inject constructor(private val repository: JournalEntryRepository) :
    ViewModel() {

    suspend fun addEntry(entryEntity: JournalEntryEntity) {
        repository.insertJournalEntry(entryEntity)
    }

}