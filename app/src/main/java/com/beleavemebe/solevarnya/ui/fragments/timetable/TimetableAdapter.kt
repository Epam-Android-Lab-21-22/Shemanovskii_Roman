package com.beleavemebe.solevarnya.ui.fragments.timetable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemDayOfWeekBinding
import com.beleavemebe.solevarnya.databinding.ListItemTimetableEntryBinding
import com.beleavemebe.solevarnya.model.TimetableEntry
import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.util.doubleFigured
import java.lang.IllegalArgumentException

class TimetableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemsAndHeaders = mutableListOf<Any>()

    fun updateItems(items: List<TimetableEntry>) {
        itemsAndHeaders = insertHeaders(items)
    }

    fun itemAt(i: Int) = itemsAndHeaders.getOrNull(i)

    private fun insertHeaders(items: List<TimetableEntry>): MutableList<Any> {
        val itemsGroupedByDayOfWeek = items.groupBy { it.dayOfWeek }
        return itemsGroupedByDayOfWeek.keys
            .flatMap { key ->
                listOf(key) + itemsGroupedByDayOfWeek[key]!!
            }.toMutableList()
    }

    override fun getItemCount(): Int = itemsAndHeaders.size

    override fun getItemViewType(position: Int): Int =
        when (itemsAndHeaders[position]) {
            is DayOfWeek -> TimetableEntryViewType.DAY_OF_WEEK.ordinal
            is TimetableEntry -> TimetableEntryViewType.TIMETABLE_ENTRY.ordinal
            else -> throw IllegalArgumentException("Unknown type encountered in item pile")
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TimetableEntryViewType.DAY_OF_WEEK.ordinal -> createDayOfWeekViewHolder(inflater, parent)
            TimetableEntryViewType.TIMETABLE_ENTRY.ordinal -> createTimetableEntryViewHolder(inflater, parent)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsAndHeaders[position]
        when (holder) {
            is DayOfWeekViewHolder -> holder.bind(item as DayOfWeek)
            is TimetableEntryViewHolder -> {
                val isLastLessonOfTheDay = itemAt(position + 1).let { it == null || it is DayOfWeek }
                holder.bind(item as TimetableEntry, isLastLessonOfTheDay)
            }
        }
    }

    enum class TimetableEntryViewType {
        TIMETABLE_ENTRY,
        DAY_OF_WEEK
    }

    private fun createTimetableEntryViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TimetableEntryViewHolder {
        val binding = ListItemTimetableEntryBinding.inflate(inflater, parent, false)
        return TimetableEntryViewHolder(binding)
    }

    inner class TimetableEntryViewHolder(
        private val binding: ListItemTimetableEntryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: TimetableEntry, isLastLessonOfTheDay: Boolean) {
            val c = binding.root.context

            binding.tvSubject.text = entry.subject.name

            binding.tvTeacher.text =
                c.getString(R.string.single_space_placeholder, entry.teacher.name, entry.teacher.surname)

            binding.tvLessonType.text =
                c.getString(entry.lessonType.stringRes)

            binding.tvHourMinuteBeginning.text =
                c.getString(
                    R.string.hour_minute_placeholder,
                    entry.hour.doubleFigured(),
                    entry.minute.doubleFigured()
                )

            binding.tvHourMinuteEnd.text =
                entry.calcEndTime().run {
                    c.getString(
                        R.string.hour_minute_placeholder,
                        first.doubleFigured(),
                        second.doubleFigured()
                    )
                }

            binding.divider.isVisible = !isLastLessonOfTheDay
        }
    }

    private fun createDayOfWeekViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): DayOfWeekViewHolder {
        val binding = ListItemDayOfWeekBinding.inflate(inflater, parent, false)
        return DayOfWeekViewHolder(binding)
    }

    inner class DayOfWeekViewHolder(
        private val binding: ListItemDayOfWeekBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayOfWeek: DayOfWeek) {
            val c = binding.root.context

            binding.tvDayOfWeek.text = c.getString(dayOfWeek.stringRes)
        }
    }
}
