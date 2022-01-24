package com.beleavemebe.solevarnya.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.data.SongDetailsHardcodedRepository
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
