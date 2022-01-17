package com.beleavemebe.solevarnya.presentation.ui.fragments.search.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearMarginDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        outRect.bottom = margin
        if (position == 0) {
            outRect.top = margin
        }
    }
}
