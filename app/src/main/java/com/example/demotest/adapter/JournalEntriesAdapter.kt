package com.example.demotest.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demotest.databinding.ItemJournalEntryBinding
import com.example.demotest.dto.GroupedJournalEntries

class JournalEntriesAdapter(
    private val entries: List<GroupedJournalEntries>
) :
    RecyclerView.Adapter<JournalEntriesAdapter.MonthViewHolder>() {

    private lateinit var binding: ItemJournalEntryBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MonthViewHolder {
        binding =
            ItemJournalEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonthViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        val item = entries[position]
        holder.bind(item)
    }

    override fun getItemCount() = entries.size

    class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemJournalEntryBinding.bind(itemView)
        private var averageMood = 0.0
        @SuppressLint("SetTextI18n")
        fun bind(items: GroupedJournalEntries) {
            binding.monthTitle.text = items.monthName
            var totalSize = 0
            for (groupedEntriesByDay in items.days) {
                totalSize += groupedEntriesByDay.entries.size
            }
            binding.monthCount.text = "$totalSize entries"
            binding.daysRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = JournalEntryDayAdapter(items.days)
            }
            for (item in items.days) {
                for (entity in item.entries) {
                    if (entity.mood.uppercase() == "SAD")
                        averageMood += 1.0
                    else if (entity.mood.uppercase() == "NEUTRAL")
                        averageMood += 2.0
                    else if (entity.mood.uppercase() == "HAPPY")
                        averageMood += 3.0

                }
            }

            if (averageMood / items.days.size > 2.5)
                binding.headerOfMonth.setBackgroundColor(Color.parseColor("#7bff37"))
            else if (averageMood / items.days.size == 2.5)
                binding.headerOfMonth.setBackgroundColor(Color.parseColor("#e4d00a"))
            else if (averageMood / items.days.size < 2.5)
                binding.headerOfMonth.setBackgroundColor(Color.parseColor("#e00707"))
        }
    }
}