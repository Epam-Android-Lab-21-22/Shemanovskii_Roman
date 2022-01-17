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
import com.beleavemebe.solevarnya.presentation.ui.fragments.Constants.ITEM_MARGIN

class TimetableFragment :
    Fragment(R.layout.fragment_timetable),
    TimetableContract.View
{
    private val binding by viewBinding(FragmentTimetableBinding::bind)
    private val presenter = TimetablePresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        presenter.onRecyclerReady()
    }

    private fun initRecycler() {
        binding.rvTimetable.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTimetable.addItemDecoration(TimetableDecoration(ITEM_MARGIN))
    }

    override fun setContent(content: List<Lesson>) {
        val timetableAdapter = TimetableAdapter(content, presenter::onLoadMoreClicked)
        binding.rvTimetable.adapter = timetableAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showDisappointmentToast() {
        Toast.makeText(
            requireContext(),
            "Not implemented",
            Toast.LENGTH_SHORT
        ).show()
    }
}
