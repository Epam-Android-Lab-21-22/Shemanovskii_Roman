package com.beleavemebe.solevarnya.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = supportFragmentManager.findFragmentById(R.id.nav_host)!!.findNavController()

        setupToolbar(navController)
        setSupportActionBar(binding.mainToolbar)

        setupBottomNavigation(navController)
    }

    private fun setupToolbar(navController: NavController) {
        val appBarConfiguration = configureTopBar()
        NavigationUI.setupWithNavController(binding.mainToolbar, navController, appBarConfiguration)
    }

    private fun configureTopBar(): AppBarConfiguration {
        val topLevelDestinations = setOf(R.id.fragment_timetable, R.id.fragment_search)
        return AppBarConfiguration(topLevelDestinations)
    }

    private fun setupBottomNavigation(navController: NavController) {
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}
