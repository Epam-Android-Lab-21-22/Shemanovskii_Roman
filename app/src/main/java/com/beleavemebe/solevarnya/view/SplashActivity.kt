package com.beleavemebe.solevarnya.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.data.SongDetailsHardcodedRepository
import com.beleavemebe.solevarnya.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivitySplashBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        lifecycleScope.launch {
            binding.tvProgressStatus.text = getString(R.string.initializing)
            delay(1500)
            SongDetailsHardcodedRepository.init(assets)
            binding.tvProgressStatus.text = getString(R.string.please_wait)
            delay(1500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
