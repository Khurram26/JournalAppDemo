package com.example.demotest.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demotest.model.JournalEntryEntity

@Dao
interface JournalEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournalEntry(journalEntry: JournalEntryEntity)

    @Query("SELECT * FROM journal_entries")
    fun getAllJournalEntries(): LiveData<List<JournalEntryEntity>>

    @Query("SELECT * FROM journal_entries WHERE id = :id")
    fun getJournalEntryById(id: Long): LiveData<JournalEntryEntity>

    @Delete
    suspend fun deleteJournalEntry(journalEntry: JournalEntryEntity)

    @Query("DELETE FROM journal_entries")
    suspend fun deleteAllJournalEntries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(journalEntries: List<JournalEntryEntity>)
}