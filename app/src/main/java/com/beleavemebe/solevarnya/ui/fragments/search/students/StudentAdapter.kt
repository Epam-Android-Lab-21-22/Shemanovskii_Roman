package com.beleavemebe.solevarnya.ui.fragments.search.students

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.fragmentViewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemStudentBinding
import com.beleavemebe.solevarnya.model.Student
import com.beleavemebe.solevarnya.ui.fragments.search.GenericDiffUtilItemCallback

class StudentAdapter(
    private val onMoreButtonClicked: (ImageButton, Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(studentDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemStudentBinding.inflate(inflater, parent, false)
        return StudentViewHolder(binding, onMoreButtonClicked)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.bind(student)
    }

    companion object {
        private val studentDiffCallback = GenericDiffUtilItemCallback<Student>()
    }

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
}
