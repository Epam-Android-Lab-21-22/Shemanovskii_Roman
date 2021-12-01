package com.beleavemebe.solevarnya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val randomPhrase: String by randomPhrases()
    private val binding by viewBinding(ActivityMainBinding::bind)

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.btnGenerate.setOnClickListener {
            showNextRandomPhrase()
        }
    }

    private fun showNextRandomPhrase() {
        binding.tvResult.text = randomPhrase
        counter += 1
        updateCountTv()
    }

    private fun updateCountTv() {
        binding.tvCounter.text = getString(R.string.generated_count_placeholder, counter)
    }
}