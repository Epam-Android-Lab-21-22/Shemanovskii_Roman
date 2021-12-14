package com.beleavemebe.solevarnya

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.File

const val PREFIX_TEMP_PHOTO = "temp_photo"

class FeatureViewModel(application: Application) : AndroidViewModel(application) {
    private val mutableState = MutableLiveData<State>(State.WaitingForImage)
    val state: LiveData<State> = mutableState

    sealed class State {
        object WaitingForImage : State()
        class CapturingImage(val imgToCaptureUri: Uri) : State()
        class DisplayingImage(val capturedImgUri: Uri) : State()
        class InvertingImage(val imgToInvertUri: Uri) : State()
        class DisplayingInvertedImage(val bitmap: Bitmap) : State()
    }

    var photoUri: Uri? = null
        private set

    fun onExecuteFeature() {
        val uri = getNewTempFileUri()
        photoUri = uri
        mutableState.value = State.CapturingImage(uri)
    }

    fun onPhotoCaptured() {
        photoUri?.let {
            mutableState.value = State.DisplayingImage(it)
        }
    }

    fun onInvertImage() {
        mutableState.value = State.InvertingImage(photoUri!!)
    }

    fun onImageInverted(invBitmap: Bitmap) {
        mutableState.value = State.DisplayingInvertedImage(invBitmap)
    }

    private fun getNewTempFileUri(): Uri {
        val tmpFile = File.createTempFile(
            PREFIX_TEMP_PHOTO,
            ".png",
            context.cacheDir
        ).apply {
            createNewFile()
            deleteOnExit() // doesn't delete :(
        }

        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            tmpFile
        )
    }

    private val context: Context
        get() = getApplication<Application>().applicationContext
}
