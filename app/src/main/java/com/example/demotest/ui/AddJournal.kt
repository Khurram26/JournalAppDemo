package com.example.demotest.ui

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demotest.databinding.ActivityAddJournalBinding
import com.example.demotest.model.JournalEntryEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddJournal : AppCompatActivity() {
    private lateinit var binding: ActivityAddJournalBinding
    private val viewModel: AddJournalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            if(!binding.noteET.text.isNullOrEmpty()){

                // Getting the checked radio button id
                // from the radio group
                val selectedOption: Int = binding.radioGroup!!.checkedRadioButtonId

                // Assigning id of the checked radio button
                val radionButton = findViewById<RadioButton>(selectedOption)

                val currentDate = Date()
                val dateFormat = SimpleDateFormat("yyyy/MM/dd")
                val dateString = dateFormat.format(currentDate)
                val timeFormat = SimpleDateFormat("HH:mm")
                val timeString = timeFormat.format(currentDate)

                lifecycleScope.launch {
                    viewModel.addEntry(JournalEntryEntity(date = dateString, time = timeString, mood = radionButton.text.toString(), notes = binding.noteET.text.toString()))
                    finish()
                }
            }
        }
    }
}