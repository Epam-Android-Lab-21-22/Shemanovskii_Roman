package com.beleavemebe.solevarnya.ui.fragments.timetable

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.model.enums.DayOfWeek

class TimetableDecoration(
    private val margin: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = parent.adapter as? TimetableAdapter
        val position = parent.getChildAdapterPosition(view)
        val item = adapter?.itemAt(position) ?: return

        when (item) {
            is TimetableAdapter.Entry.Header -> {
                outRect.top = margin
            }
            is TimetableAdapter.Entry.Lesson -> {
                /* No margins */
            }
        }
    }
}