package com.beleavemebe.solevarnya.repository

interface ItemRepository<T> {
    fun fetchAll(): List<T>
}
