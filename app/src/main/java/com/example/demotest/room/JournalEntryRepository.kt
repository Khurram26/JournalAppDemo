package com.example.demotest.room

import androidx.lifecycle.LiveData
import com.example.demotest.model.JournalEntryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JournalEntryRepository @Inject constructor(private val journalEntryDao: JournalEntryDao) {

    suspend fun insertJournalEntry(journalEntry: JournalEntryEntity) {
        withContext(Dispatchers.IO) {
            journalEntryDao.insertJournalEntry(journalEntry)
        }
    }

    fun getJournalEntryById(id: Long): LiveData<JournalEntryEntity> {
        return journalEntryDao.getJournalEntryById(id)
    }

    suspend fun deleteJournalEntry(journalEntry: JournalEntryEntity) {
        withContext(Dispatchers.IO) {
            journalEntryDao.deleteJournalEntry(journalEntry)
        }
    }

    suspend fun deleteAllJournalEntries() {
        withContext(Dispatchers.IO) {
            journalEntryDao.deleteAllJournalEntries()
        }
    }

    fun getAllJournalEntries(): LiveData<List<JournalEntryEntity>> {
        return journalEntryDao.getAllJournalEntries()
    }


    suspend fun insertAll(journalEntries: List<JournalEntryEntity>) {
        withContext(Dispatchers.IO) {
            journalEntryDao.insertAll(journalEntries)
        }
    }
}
