package com.beleavemebe.solevarnya

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityFeatureBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import android.Manifest.permission.CAMERA as PERMISSION

class FeatureActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityFeatureBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        binding.button.setOnClickListener {
            tryDoFeature()
        }
    }

    private fun tryDoFeature() {
        val permission = PERMISSION
        when (checkPermission(permission)) {
            PermissionStatus.NOT_REQUESTED -> requestPermission(permission)
            PermissionStatus.NEEDS_EXPLANATION -> showRationale(permission)
            PermissionStatus.DENIED -> failGracefully()
            PermissionStatus.GRANTED -> doFeature()
        }
    }

    private fun requestPermission(permission: String) {
        requestPermissionLauncher.launch(permission)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                doFeature()
            } else {
                failGracefully()
            }
        }

    private fun showRationale(permission: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.rationale))
            .setMessage(getString(R.string.rationale_msg))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> requestPermission(permission) }
            .show()
    }

    private fun failGracefully() {
        Toast.makeText(this, "We respect your decision", Toast.LENGTH_SHORT).show()
    }

    private fun doFeature() {
        Toast.makeText(this, "Not yet implemented xd", Toast.LENGTH_SHORT).show()
    }

    private fun checkPermission(permission: String): PermissionStatus {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            PermissionStatus.GRANTED
        } else if (checkPermissionCompat(permission)) {
            PermissionStatus.GRANTED
        } else if (shouldShowRequestPermissionRationale(permission)) {
            PermissionStatus.NEEDS_EXPLANATION
        } else {
            PermissionStatus.NOT_REQUESTED
        }
    }

    private fun checkPermissionCompat(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    enum class PermissionStatus {
        DENIED,
        NOT_REQUESTED,
        NEEDS_EXPLANATION,
        GRANTED,
    }
}
