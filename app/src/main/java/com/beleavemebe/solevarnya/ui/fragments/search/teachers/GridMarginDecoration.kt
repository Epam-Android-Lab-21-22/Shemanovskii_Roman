package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMarginDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        configureVerticalMargins(outRect, position)
        configureHorizontalMargins(outRect, position)
    }

    private fun configureVerticalMargins(outRect: Rect, position: Int) {
        outRect.bottom = margin
        if (position == 0 || position == 1) {
            outRect.top = margin
        }
    }

    private fun configureHorizontalMargins(outRect: Rect, position: Int) {
        if (position % 2 == 0) {
            outRect.left = margin
            outRect.right = margin / 2
        } else {
            outRect.right = margin
            outRect.left = margin / 2
        }
    }
}