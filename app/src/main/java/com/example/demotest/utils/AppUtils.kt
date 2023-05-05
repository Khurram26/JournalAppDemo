package com.example.demotest.utils

import android.annotation.SuppressLint
import com.example.demotest.dto.GroupedJournalEntries
import com.example.demotest.dto.GroupedJournalEntriesByDay
import com.example.demotest.model.JournalEntryEntity
import java.text.SimpleDateFormat

object AppUtils {
    @SuppressLint("SimpleDateFormat")
    fun convertDtoToGroupData(journalEntityList: List<JournalEntryEntity>): MutableList<GroupedJournalEntries> {
        val entriesByMonthAndDay = mutableListOf<GroupedJournalEntries>()
        journalEntityList.forEach { entry ->

            val dateFormat = SimpleDateFormat("yyyy/MM/dd")
            val date = dateFormat.parse(entry.date)
            val dayOfWeek = date?.let { SimpleDateFormat("EEEE").format(it) }
            val dayOfMonth = date?.let { SimpleDateFormat("MMMM").format(it) }


            val dateParts = entry.date.split("/")
            val month = dateParts[1]
            val day = dateParts[2]

            val monthEntry = entriesByMonthAndDay.find { it.month == month }
            if (monthEntry == null) {
                entriesByMonthAndDay.add(
                    GroupedJournalEntries(
                        month = month, monthName = dayOfMonth!!, days = listOf(
                            GroupedJournalEntriesByDay(
                                day = day,
                                dayName = dayOfWeek!!,
                                entries = listOf(entry)
                            )
                        )
                    )
                )
            } else {
                val dayEntry = monthEntry.days.find { it.day == day }
                if (dayEntry == null) {
                    monthEntry.days = monthEntry.days + GroupedJournalEntriesByDay(
                        day = day, dayName = dayOfWeek!!, entries = listOf(entry)
                    )
                } else {
                    dayEntry.entries = dayEntry.entries + entry
                }
            }
        }
        return entriesByMonthAndDay
    }
}