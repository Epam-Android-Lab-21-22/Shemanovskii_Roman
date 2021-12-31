package com.beleavemebe.solevarnya.util

import com.github.javafaker.Faker
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

fun getFaker(): Faker {
    return Faker(Locale.getDefault())
}

fun illegalState(msg: String): Nothing =
    throw IllegalStateException(msg)

fun illegalArgument(msg: String): Nothing =
    throw IllegalArgumentException(msg)

fun <E> MutableList<E>.swap(from: Int, to: Int) {
    add(to, removeAt(from))
}

fun Int.doubleFigured(): String {
    return if (this >= 10) "$this" else "0$this"
}
