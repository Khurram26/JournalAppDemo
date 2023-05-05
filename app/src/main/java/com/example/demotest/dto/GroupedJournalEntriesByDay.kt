package com.example.demotest.dto

import com.example.demotest.model.JournalEntryEntity

data class GroupedJournalEntriesByDay(
    val day: String,
    var dayCount: String="",
    var dayName:String,
    var entries: List<JournalEntryEntity>
)