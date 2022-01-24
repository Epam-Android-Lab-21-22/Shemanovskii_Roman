package com.beleavemebe.solevarnya.view.util

import androidx.appcompat.widget.SearchView

fun SearchView.doOnQueryChanged(onQueryChange: (String) -> Unit) {
    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                onQueryChange(newText)
                return true
            }
        }
    )
}
