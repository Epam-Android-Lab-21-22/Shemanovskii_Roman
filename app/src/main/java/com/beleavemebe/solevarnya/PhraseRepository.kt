package com.beleavemebe.solevarnya

import android.content.Context

object PhraseRepository {
    fun getPhrase(context: Context): String =
        context.resources.run {
            val firstHalf = getStringArray(R.array.advice_first_halfs).random()
            val secondHalf = getStringArray(R.array.advice_second_halfs).random()
            return "$firstHalf $secondHalf"
        }
}