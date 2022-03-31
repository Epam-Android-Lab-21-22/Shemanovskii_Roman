package com.github.beleavemebe.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

sealed class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {
    class FirstFragment : BaseFragment(R.layout.fragment_first)
    class SecondFragment : BaseFragment(R.layout.fragment_second)
    class ThirdFragment : BaseFragment(R.layout.fragment_third)

    private var stackPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackPosition = arguments?.getInt(KEY_STACK_POSITION) ?: 0
    }

    override fun onViewCreated(
        rootView: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(rootView, savedInstanceState)

        initLayerTextView(rootView)
        initRootViewListener(rootView)
    }

    private fun initLayerTextView(root: View) {
        root.findViewById<TextView>(R.id.tv_counter).text = stackPosition.toString()
    }

    private fun initRootViewListener(root: View) {
        root.setOnClickListener {
            (requireActivity() as MainActivity)
                .onFragmentClicked(
                    this,
                    stackPosition ?: throw IllegalStateException("Unknown stack position")
                )
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity)
            .run {
                supportActionBar?.title = getTagFor(this@BaseFragment)
            }
    }

    companion object {
        const val KEY_STACK_POSITION = "stack_position"
    }
}
