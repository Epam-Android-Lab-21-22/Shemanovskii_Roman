package com.beleavemebe.solevarnya.ui.fragments.search.students

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemStudentBinding
import com.beleavemebe.solevarnya.model.Student

class StudentViewHolder(
    private val binding: ListItemStudentBinding,
    private val onMoreButtonClicked: (ImageButton, Student) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(student: Student) {
        val c = binding.root.context

        binding.tvNameSurname.text = c.getString(
            R.string.single_space_placeholder,
            student.name,
            student.surname
        )

        binding.tvDegreeGroup.text = c.getString(
            R.string.single_space_placeholder,
            c.getString(student.degree.stringResId),
            student.group
        )

        with(binding.ibMore) {
            setOnClickListener {
                onMoreButtonClicked(this, student)
            }
        }
    }
}