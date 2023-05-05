package com.example.demotest.ui

import com.example.demotest.model.JournalEntryEntity
import com.example.demotest.room.JournalEntryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddJournalViewModelTest {

    @Mock
    lateinit var repository: JournalEntryRepository

    lateinit var viewModel: AddJournalViewModel

    @Before
    fun setup() {
        viewModel = AddJournalViewModel(repository)
    }

    @Test
    fun `addEntry inserts entry into repository`() = runBlocking {
        // Create test data
        val entry = JournalEntryEntity(date =  "2022/1/15", mood = "Neutralt", notes = "I am yoo", time = "18:00")

        // Call function under test
        viewModel.addEntry(entry)

        // Verify that insertJournalEntry() is called with the expected argument
        verify(repository).insertJournalEntry(entry)
    }
}
