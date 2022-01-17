package com.beleavemebe.solevarnya.presentation.ui.fragments.timetable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Lesson
import com.beleavemebe.solevarnya.databinding.FragmentTimetableBinding
import com.beleavemebe.solevarnya.presentation.repository.InMemoryLessonDataSource
import com.beleavemebe.solevarnya.presentation.ui.fragments.BaseListView
import com.beleavemebe.solevarnya.presentation.ui.fragments.Constants.ITEM_MARGIN

class TimetableFragment :
    Fragment(R.layout.fragment_timetable),
    TimetableContract.View
{
    private val binding by viewBinding(FragmentTimetableBinding::bind)
    private val presenter = TimetablePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onRecyclerReady()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTimetable.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(TimetableDecoration(ITEM_MARGIN))
        }
    }

    override fun setContent(content: List<Lesson>) {
        binding.rvTimetable.apply {
            val timetableAdapter = TimetableAdapter(content, ::loadMore)
            adapter = timetableAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onRecyclerReady()
    }

    private fun loadMore() {
        Toast.makeText(
            requireContext(),
            "Removed until better times xD",
            Toast.LENGTH_SHORT
        ).show()
    }
}
