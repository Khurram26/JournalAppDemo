package com.example.demotest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demotest.model.JournalEntryEntity
import com.example.demotest.room.JournalEntryRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: JournalEntryRepository) :
    ViewModel() {

    val journalEntries: LiveData<List<JournalEntryEntity>> = repository.getAllJournalEntries()

    fun loadDataFromJson(context: Context) {
        viewModelScope.launch {
            val json =
                context.assets.open("journal_entries.json").bufferedReader().use { it.readText() }
            val journalEntries =
                Gson().fromJson(json, Array<JournalEntryEntity>::class.java).toList()
            repository.insertAll(journalEntries)
        }
    }

    suspend fun deleteEntry() {
        repository.deleteAllJournalEntries()
    }
}