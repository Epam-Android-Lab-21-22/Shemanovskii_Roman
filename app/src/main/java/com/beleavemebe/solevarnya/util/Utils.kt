package com.beleavemebe.solevarnya.util

import com.github.javafaker.Faker
import java.lang.IllegalStateException
import java.util.*

fun getFaker(): Faker {
    return Faker(Locale("ru", "russia"))
}

fun illegalState(msg: String): Nothing =
    throw IllegalStateException(msg)

fun <E> MutableList<E>.swap(from: Int, to: Int) {
    add(to, removeAt(from))
}
