package com.beleavemebe.solevarnya

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
        observeNetworkState()
    }

    private fun initListeners() {
        binding.btnProceed.setOnClickListener {
            val intent = Intent(this, FeatureActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeNetworkState() {
        viewModel.networkState.observe(this) { state ->
            when (state) {
                is MainViewModel.NetworkState.Disconnected -> onNoNetwork()
                is MainViewModel.NetworkState.Connected -> onNetworkAvailable()
                is MainViewModel.NetworkState.Unknown -> onNetworkStatusUnknown()
            }
        }
    }

    private fun onNetworkStatusUnknown() {
        binding.ivStatus.setImageDrawable(drawable(R.drawable.internet_status_unknown))
        binding.textview.text = getString(R.string.network_status_unknown)
        binding.btnProceed.isEnabled = false
    }

    private fun onNoNetwork() {
        binding.ivStatus.setImageDrawable(drawable(R.drawable.ic_internet_offline))
        binding.textview.text = getString(R.string.network_status_no_network)
        binding.btnProceed.isEnabled = false

        Snackbar.make(
            binding.btnProceed,
            getString(R.string.disconnected),
            Snackbar.LENGTH_SHORT
        ).setAnchorView(binding.btnProceed).show()
    }

    private fun onNetworkAvailable() {
        binding.ivStatus.setImageDrawable(drawable(R.drawable.ic_internet_available))
        binding.textview.text = getString(R.string.network_status_available)
        binding.btnProceed.isEnabled = true
    }

    private fun drawable(@DrawableRes resId: Int): Drawable? {
        return AppCompatResources.getDrawable(this, resId)
    }
}
