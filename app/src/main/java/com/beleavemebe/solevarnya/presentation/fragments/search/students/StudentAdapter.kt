package com.beleavemebe.solevarnya.presentation.fragments.search.students

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.databinding.ListItemStudentBinding
import com.beleavemebe.solevarnya.framework.model.DegreeResourceEnum
import com.beleavemebe.solevarnya.framework.model.ProgramResourceEnum
import com.beleavemebe.solevarnya.presentation.fragments.search.GenericDiffUtilItemCallback
import com.bumptech.glide.Glide

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

    class StudentViewHolder(
        private val binding: ListItemStudentBinding,
        private val onMoreButtonClicked: (ImageButton, Student) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            val c = binding.root.context

            Glide.with(c)
                .load(student.avatarUrl)
                .placeholder(R.drawable.person_placeholder)
                .into(binding.ivAvatar)

            binding.tvNameSurname.text =
                c.getString(
                R.string.single_space_placeholder,
                student.name,
                student.surname
            )
            binding.tvDegreeGroup.text =
                c.getString(
                    R.string.single_space_placeholder,
                    c.getString(DegreeResourceEnum.from(student.degree).stringResId),
                    c.getString(
                        R.string.group_placeholder,
                        student.admissionYear,
                        c.getString(ProgramResourceEnum.from(student.program).stringResId),
                        student.groupNumber
                    )
                )
            binding.ibMore.setOnClickListener {
                onMoreButtonClicked(binding.ibMore, student)
            }
            binding.root.setOnClickListener {
                binding.tvDegreeGroup.text = student.quote
            }
        }
    }

    companion object {
        private val studentDiffCallback = GenericDiffUtilItemCallback<Student>()
    }
}