package com.beleavemebe.solevarnya.presentation.ui.fragments.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.FragmentSearchBinding
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.students.StudentsFragment
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.subjects.SubjectsFragment
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.teachers.TeachersFragment
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initBackPressedListener()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = PagerAdapter(requireActivity())
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            tab.text = PagerAdapter.getFragmentTitle(position, requireContext())
        }.attach()
    }

    private fun initBackPressedListener() {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner) {
                if (binding.viewPager.currentItem == 0) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                } else {
                    binding.viewPager.currentItem = 0
                }
            }
    }

    private class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int =
            getRecyclerFragmentCount()

        override fun createFragment(position: Int): Fragment =
            createRecyclerFragment(position)

        companion object {
            private val PAGER_FRAGMENTS = arrayOf(
                ::StudentsFragment to R.string.students,
                ::TeachersFragment to R.string.teachers,
                ::SubjectsFragment to R.string.subjects,
            )

            fun getRecyclerFragmentCount(): Int =
                PAGER_FRAGMENTS.size

            fun createRecyclerFragment(i: Int): Fragment =
                PAGER_FRAGMENTS[i].first.invoke()

            fun getFragmentTitle(i: Int, context: Context): String =
                context.getString(PAGER_FRAGMENTS[i].second)
        }
    }
}
