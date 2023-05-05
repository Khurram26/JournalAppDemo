package com.example.demotest.di

import android.content.Context
import androidx.room.Room
import com.example.demotest.room.JournalDatabase
import com.example.demotest.room.JournalEntryDao
import com.example.demotest.room.JournalEntryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJournalRepository(database: JournalDatabase): JournalEntryRepository {
        return JournalEntryRepository(database.journalEntryDao())
    }

    @Provides
    fun provideJournalDao(database: JournalDatabase): JournalEntryDao {
        return database.journalEntryDao()
    }

    @Provides
    fun provideJournalDatabase(@ApplicationContext appContext: Context): JournalDatabase {
        return Room.databaseBuilder(
            appContext,
            JournalDatabase::class.java,
            "journal_database"
        ).build()
    }
}