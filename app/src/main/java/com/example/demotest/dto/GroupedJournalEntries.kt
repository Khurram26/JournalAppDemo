package com.example.demotest.dto

data class GroupedJournalEntries(
    val month: String,
    var monthCount: String="",
    var monthName: String,
    var days: List<GroupedJournalEntriesByDay>
)