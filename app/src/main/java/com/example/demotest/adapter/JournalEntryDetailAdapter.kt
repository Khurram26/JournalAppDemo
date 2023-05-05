package com.example.demotest.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demotest.databinding.ItemJournalEntryDetailBinding
import com.example.demotest.model.JournalEntryEntity

class JournalEntryDetailAdapter(private val entries: List<JournalEntryEntity>) :
    RecyclerView.Adapter<JournalEntryDetailAdapter.EntityViewHolder>() {

    private lateinit var binding: ItemJournalEntryDetailBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntityViewHolder {
        binding =
            ItemJournalEntryDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return EntityViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val item = entries[position]
        holder.bind(item)
    }

    override fun getItemCount() = entries.size

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemJournalEntryDetailBinding.bind(itemView)
        fun bind(item: JournalEntryEntity) {
            binding.timeOfEntry.text = item.time
            binding.note.text = item.notes
            when (item.mood.uppercase()) {
                "SAD" -> {
                    binding.moodColor.setBackgroundColor(Color.parseColor("#e00707"))
                }
                "NEUTRAL" -> {
                    binding.moodColor.setBackgroundColor(Color.parseColor("#e4d00a"))
                }
                "HAPPY" -> {
                    binding.moodColor.setBackgroundColor(Color.parseColor("#7bff37"))
                }
            }
        }
    }
}