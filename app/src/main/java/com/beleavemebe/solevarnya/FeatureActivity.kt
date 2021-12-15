package com.beleavemebe.solevarnya

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.databinding.ActivityFeatureBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.concurrent.thread

import android.Manifest.permission.CAMERA as CAMERA_PERMISSION


class FeatureActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityFeatureBinding::bind)
    private val viewModel by lazy { ViewModelProvider(this).get(FeatureViewModel::class.java) }
    private val handler: Handler
        get() = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

        initListeners()
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.state.observe(this) { state ->
            Log.d("FeatureActivity", "$state")
            when (state) {
                is FeatureViewModel.State.WaitingForImage -> {}
                is FeatureViewModel.State.DisplayingImage -> {
                    displayImage(state.capturedImgUri)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        initInvertButton()
                    }
                }
                is FeatureViewModel.State.InvertingImage -> {
                    displayImage(state.imgToInvertUri)
                    invertPhoto(state.imgToInvertUri) { invertedBitmap ->
                        viewModel.onImageInverted(invertedBitmap)
                    }
                }
                is FeatureViewModel.State.DisplayingInvertedImage -> {
                    displayImage(state.bitmap)
                    showInvertButton()
                    disableInvertButton()
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnUseFeature.setOnClickListener {
            doFeature()
        }
    }

    private fun doFeature() {
        val permission = CAMERA_PERMISSION
        when (checkPermission(permission)) {
            PermissionStatus.NOT_REQUESTED -> requestPermission(permission)
            PermissionStatus.NEEDS_EXPLANATION -> showRationale(permission)
            PermissionStatus.DENIED -> failGracefully()
            PermissionStatus.GRANTED -> executeFeature()
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
                executeFeature()
            } else {
                failGracefully()
            }
        }

    private fun failGracefully() {
        Toast.makeText(
            this,
            getString(R.string.respect),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showRationale(permission: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.rationale))
            .setMessage(getString(R.string.rationale_msg))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> requestPermission(permission) }
            .show()
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
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    enum class PermissionStatus {
        DENIED,
        NOT_REQUESTED,
        NEEDS_EXPLANATION,
        GRANTED,
    }

    private fun executeFeature() {
        viewModel.onExecuteFeature()
        takePhotoLauncher.launch(viewModel.photoUri)
    }

    private val takePhotoLauncher =
        registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                viewModel.onPhotoCaptured()
            }
        }

    private fun displayImage(capturedImgUri: Uri) {
        binding.ivPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.ivPhoto.setImageURI(capturedImgUri)
    }

    private fun displayImage(bitmap: Bitmap) {
        binding.ivPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.ivPhoto.setImageBitmap(bitmap)
    }

    private fun initInvertButton() {
        binding.btnInvertColors.isVisible = true
        binding.btnInvertColors.setOnClickListener {
            viewModel.onInvertImage()
        }
    }

    private fun hideInvertButton() {
        binding.btnInvertColors.visibility = View.INVISIBLE
        binding.progressInvert.isIndeterminate = true
        binding.progressInvert.isVisible = true
    }

    private fun showInvertButton() {
        binding.btnInvertColors.isVisible = true
        binding.progressInvert.isVisible = false
    }

    private fun disableInvertButton() {
        binding.btnInvertColors.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.already_inverted),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun invertPhoto(imgToInvertUri: Uri, onBitmapInverted: (Bitmap) -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        hideInvertButton()
        thread {
            val bitmap = mutableBitmapFromUri(imgToInvertUri) ?: return@thread
            bitmap.invertColors()
            handler.post {
                onBitmapInverted(bitmap)
            }
        }
    }
}
