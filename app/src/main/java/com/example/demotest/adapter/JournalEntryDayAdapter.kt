package com.example.demotest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demotest.databinding.ItemJournalDayBinding
import com.example.demotest.dto.GroupedJournalEntriesByDay

class JournalEntryDayAdapter(private val entries: List<GroupedJournalEntriesByDay>) :
    RecyclerView.Adapter<JournalEntryDayAdapter.DayViewHolder>() {

    private lateinit var binding: ItemJournalDayBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DayViewHolder {
        binding =
            ItemJournalDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val item = entries[position]
        holder.bind(item)
    }

    override fun getItemCount() = entries.size

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemJournalDayBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bind(item: GroupedJournalEntriesByDay) {
            binding.dayTitle.text = item.dayName+", "+item.day
            binding.dayCount.text = item.entries.size.toString()+" entries"
            binding.entriesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = JournalEntryDetailAdapter(item.entries)
            }
        }
    }
}