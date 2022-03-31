package com.github.beleavemebe.app_navigation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi

fun Context.mutableBitmapFromUri(uri: Uri): Bitmap? {
    val inputStream = contentResolver.openInputStream(uri)
    val options = BitmapFactory.Options().apply {
        inMutable = true
    }
    return BitmapFactory.decodeStream(inputStream, null, options)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Bitmap.invertColors() {
    for (j in 0 until height) {
        for (i in 0 until width) {
            val argb = getPixel(i, j)
            val invArgb = Color.valueOf(argb).invert().toArgb()
            setPixel(i, j, invArgb)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Color.invert(): Color {
    return Color.valueOf(255f - red(), 255f - green(), 255f - blue())
}
