package com.example.demotest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demotest.adapter.JournalEntriesAdapter
import com.example.demotest.databinding.ActivityMainBinding
import com.example.demotest.ui.AddJournal
import com.example.demotest.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiCallingMock()

        viewModel.journalEntries.observe(this, Observer { list ->
            binding.entriesRV.layoutManager = LinearLayoutManager(this)
            val adapter = JournalEntriesAdapter(AppUtils.convertDtoToGroupData(list))
            binding.entriesRV.adapter = adapter

        })

        binding.addJournal.setOnClickListener {
            startActivity(Intent(this,AddJournal::class.java))
        }

    }

    //Calling API and saving data in Room
    private fun apiCallingMock() {
        viewModel.loadDataFromJson(this)
    }
}