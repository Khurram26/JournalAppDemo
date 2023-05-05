package com.example.demotest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demotest.model.JournalEntryEntity

@Database(entities = [JournalEntryEntity::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {

    abstract fun journalEntryDao(): JournalEntryDao

    companion object {
        @Volatile
        private var INSTANCE: JournalDatabase? = null

        fun getDatabase(context: Context): JournalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JournalDatabase::class.java,
                    "journal_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}