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
import com.beleavemebe.solevarnya.util.illegalArgument

class TimetableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_LESSON = 0
        const val VIEW_TYPE_HEADER = 1
    }

    sealed class Entry(val id: Int) {
        class Lesson(val entry: TimetableEntry) : Entry(VIEW_TYPE_LESSON)
        class Header(val dayOfWeek: DayOfWeek) : Entry(VIEW_TYPE_HEADER)
    }

    private val itemsAndHeaders = mutableListOf<Entry>()

    fun updateItems(items: List<TimetableEntry>) {
        itemsAndHeaders.clear()
        itemsAndHeaders.addAll(insertHeaders(items))
    }

    fun itemAt(i: Int) = itemsAndHeaders.getOrNull(i)

    override fun getItemCount(): Int =
        itemsAndHeaders.size

    override fun getItemViewType(position: Int): Int =
        itemsAndHeaders[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> createHeader(inflater, parent)
            VIEW_TYPE_LESSON -> createLesson(inflater, parent)
            else -> illegalArgument("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsAndHeaders[position]
        when (holder) {
            is HeaderViewHolder -> bindHeader(holder, item)
            is LessonViewHolder -> bindLesson(holder, item, position)
        }
    }

    private fun insertHeaders(items: List<TimetableEntry>): MutableList<Entry> {
        val groupedByDay = items.groupBy { it.dayOfWeek }
        return groupedByDay.keys
            .flatMap { day ->
                mutableListOf<Entry>().apply {
                    add(Entry.Header(day))
                    addAll(
                        groupedByDay[day]!!.map { Entry.Lesson(it) }
                    )
                }
            }.toMutableList()
    }

    private fun createLesson(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LessonViewHolder {
        val binding = ListItemTimetableEntryBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    private fun bindLesson(
        holder: LessonViewHolder,
        item: Entry,
        position: Int
    ) {
        val entry = (item as Entry.Lesson).entry
        val isLastToday = itemAt(position + 1).let { it == null || it is Entry.Header }
        holder.bind(entry, isLastToday)
    }

    inner class LessonViewHolder(
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

    private fun createHeader(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HeaderViewHolder {
        val binding = ListItemDayOfWeekBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    private fun bindHeader(holder: HeaderViewHolder, item: Entry) {
        val dayOfWeek = (item as Entry.Header).dayOfWeek
        holder.bind(dayOfWeek)
    }

    inner class HeaderViewHolder(
        private val binding: ListItemDayOfWeekBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dayOfWeek: DayOfWeek) {
            val c = binding.root.context

            binding.tvDayOfWeek.text = c.getString(dayOfWeek.stringRes)
        }
    }
}
