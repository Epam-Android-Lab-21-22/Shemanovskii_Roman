package com.beleavemebe.solevarnya

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class RandomPhraseProperty<T> : ReadOnlyProperty<T, String>{
    override fun getValue(
        thisRef: T,
        property: KProperty<*>
    ): String =
        PhraseRepository.run {
            "$firstWord $secondWord"
        }
}

fun <T> randomPhrases() = RandomPhraseProperty<T>()