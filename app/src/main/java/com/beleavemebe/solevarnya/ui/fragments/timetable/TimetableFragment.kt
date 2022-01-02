package com.beleavemebe.solevarnya.ui.fragments.timetable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentTimetableBinding
import com.beleavemebe.solevarnya.model.Lesson
import com.beleavemebe.solevarnya.repository.LessonRepository
import com.beleavemebe.solevarnya.ui.fragments.Constants.ITEM_MARGIN

class TimetableFragment : Fragment(R.layout.fragment_timetable) {
    private val binding by viewBinding(FragmentTimetableBinding::bind)

    private var items = emptyList<Lesson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = LessonRepository.fetchAll()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTimetable.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(TimetableDecoration(ITEM_MARGIN))
            val timetableAdapter = TimetableAdapter(items)
            adapter = timetableAdapter
        }
    }
}