package com.example.demotest

import android.content.Context
import com.example.demotest.model.JournalEntryEntity
import com.example.demotest.room.JournalEntryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @Mock
    lateinit var repository: JournalEntryRepository

    @Mock
    lateinit var context: Context

    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup() {
        viewModel = MainActivityViewModel(repository)
    }

    @Test
    fun `loadDataFromJson inserts data into repository`() = runBlocking {
        // Mock asset file
        val json = "[{\"id\":1,\"title\":\"Test entry 1\",\"content\":\"Test content 1\"}]"
        val inputStream = ByteArrayInputStream(json.toByteArray())
        `when`(context.assets.open("journal_entries.json")).thenReturn(inputStream)

        // Call function under test
        viewModel.loadDataFromJson(context)

        // Verify that insertAll() is called with the expected argument
        verify(repository).insertAll(listOf(JournalEntryEntity(date =  "2022/05/15", mood = "Happy", notes = "I am Happy.", time = "22:15")))
    }

    @Test
    fun `deleteEntry calls deleteAllJournalEntries in repository`() = runBlocking {
        // Call function under test
        viewModel.deleteEntry()

        // Verify that deleteAllJournalEntries() is called
        verify(repository).deleteAllJournalEntries()
    }
}