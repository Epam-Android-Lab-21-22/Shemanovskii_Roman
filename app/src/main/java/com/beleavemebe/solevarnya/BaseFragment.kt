package com.beleavemebe.solevarnya

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.IllegalStateException

sealed class BaseFragment(@LayoutRes layoutResId: Int) : Fragment(layoutResId) {
    private var stackPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackPosition = arguments?.getInt(KEY_STACK_POSITION) ?: 0
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_counter)
            .text = stackPosition.toString()

        view.setOnClickListener {
            deepenSelf(
                stackPosition
                    ?: throw IllegalStateException("Unknown stack position")
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.let {
                it.title = this::class.java.simpleName
            }
    }

    protected abstract fun deepenSelf(stackPosition: Int)

    protected inline fun <reified T : BaseFragment> deepen(stackPosition: Int) {
        (requireActivity() as MainActivity)
            .deepenCurrentFragment<T>(stackPosition + 1)
    }

    companion object {
        const val KEY_STACK_POSITION = "stack_position"
    }
}