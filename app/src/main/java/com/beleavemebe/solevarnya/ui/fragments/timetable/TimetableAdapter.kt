package com.beleavemebe.solevarnya.ui.fragments.timetable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemDayOfWeekBinding
import com.beleavemebe.solevarnya.databinding.ListItemLessonBinding
import com.beleavemebe.solevarnya.databinding.ListItemLoadMoreBinding
import com.beleavemebe.solevarnya.model.Lesson
import com.beleavemebe.solevarnya.model.enums.DayOfWeek
import com.beleavemebe.solevarnya.util.doubleFigured
import com.beleavemebe.solevarnya.util.illegalArgument
import java.util.*

class TimetableAdapter(
    private val timetable: List<Lesson>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    sealed class TimetableEntry(val id: Int) {
        class LessonEntry(val lesson: Lesson) : TimetableEntry(VIEW_TYPE_LESSON)
        class Header(val dayOfWeek: DayOfWeek) : TimetableEntry(VIEW_TYPE_HEADER)
        object LoadMore : TimetableEntry(VIEW_TYPE_LOAD_MORE)
    }

    private val items = mutableListOf<TimetableEntry>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        loadTimetable()
    }

    private fun loadTimetable() {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        val filtered =
            timetable.filter {
                it.dayOfWeek.ordinal >= today + 1
            }.takeIf {
                it.isNotEmpty()
            } ?: timetable

        items.addAll(filtered.toDisplayableItems())
        notifyItemRangeInserted(0, itemCount)
    }

    private fun loadMore() {
        val lastIndex = items.lastIndex
        items.removeLast()
        notifyItemRemoved(lastIndex)
        items.addAll(timetable.toDisplayableItems())
        notifyItemRangeInserted(lastIndex, items.lastIndex - lastIndex)
    }

    fun itemAt(i: Int): TimetableEntry? =
        items.getOrNull(i)

    override fun getItemCount(): Int =
        items.size

    override fun getItemViewType(position: Int): Int =
        items[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> createHeader(inflater, parent)
            VIEW_TYPE_LESSON -> createLesson(inflater, parent)
            VIEW_TYPE_LOAD_MORE -> createLoadMore(inflater, parent)
            else -> illegalArgument("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is HeaderViewHolder -> bindHeader(holder, item)
            is LessonViewHolder -> bindLesson(holder, item, position)
            is LoadMoreViewHolder -> bindLoadMore(holder)
        }
    }

    private fun List<Lesson>.toDisplayableItems(): MutableList<TimetableEntry> {
        val groupedByDay = this.groupBy { lesson -> lesson.dayOfWeek }
        return groupedByDay.keys
            .flatMap { day ->
                mutableListOf<TimetableEntry>().apply {
                    add(TimetableEntry.Header(day))
                    addAll(
                        groupedByDay[day]!!.map { TimetableEntry.LessonEntry(it) }
                    )
                }
            }.toMutableList().apply {
                add(TimetableEntry.LoadMore)
            }
    }

    private fun createLesson(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): LessonViewHolder {
        val binding = ListItemLessonBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    private fun bindLesson(
        holder: LessonViewHolder,
        item: TimetableEntry,
        position: Int
    ) {
        val lesson = (item as TimetableEntry.LessonEntry).lesson
        val isLastToday =
            itemAt(position + 1).let {
                it is TimetableEntry.Header || it is TimetableEntry.LoadMore
            }

        holder.bind(lesson, isLastToday)
    }

    inner class LessonViewHolder(
        private val binding: ListItemLessonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lesson: Lesson, isLastLessonOfTheDay: Boolean) {
            val c = binding.root.context

            binding.tvSubject.text = lesson.subject.name

            binding.tvTeacher.text =
                c.getString(R.string.single_space_placeholder, lesson.teacher.name, lesson.teacher.surname)

            binding.tvLessonType.text =
                c.getString(lesson.lessonType.stringRes)

            binding.tvHourMinuteBeginning.text =
                c.getString(
                    R.string.hour_minute_placeholder,
                    lesson.hour.doubleFigured(),
                    lesson.minute.doubleFigured()
                )

            binding.tvHourMinuteEnd.text =
                lesson.calcEndTime().run {
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

    private fun bindHeader(holder: HeaderViewHolder, item: TimetableEntry) {
        val dayOfWeek = (item as TimetableEntry.Header).dayOfWeek
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

    private fun createLoadMore(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        val binding = ListItemLoadMoreBinding.inflate(inflater, parent, false)
        return LoadMoreViewHolder(binding)
    }

    private fun bindLoadMore(holder: LoadMoreViewHolder) {
        holder.bind()
    }

    inner class LoadMoreViewHolder(
        private val binding: ListItemLoadMoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.btnLoadMore.setOnClickListener {
                loadMore()
            }
        }
    }

    companion object {
        const val VIEW_TYPE_LESSON = 0
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_LOAD_MORE = 2
    }
}

