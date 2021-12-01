package com.beleavemebe.solevarnya

import android.content.Context
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class RandomPhraseProperty<T : Context> : ReadOnlyProperty<T, String>{
    override fun getValue(
        thisRef: T,
        property: KProperty<*>
    ): String = PhraseRepository.getPhrase(thisRef)
}

fun <T : Context> randomPhrases() = RandomPhraseProperty<T>()