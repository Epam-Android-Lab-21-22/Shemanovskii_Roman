package com.beleavemebe.solevarnya.presentation.fragments.search.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMarginDecoration(margin: Int) : RecyclerView.ItemDecoration() {
    private val halfMargin = margin / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.paddingLeft != halfMargin) {
            parent.setPadding(halfMargin, halfMargin, halfMargin, halfMargin)
            parent.clipToPadding = false
        }

        outRect.top = halfMargin
        outRect.bottom = halfMargin
        outRect.left = halfMargin
        outRect.right = halfMargin
    }
}
