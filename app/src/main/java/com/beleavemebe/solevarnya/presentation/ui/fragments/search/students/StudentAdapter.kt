package com.beleavemebe.solevarnya.presentation.ui.fragments.search.students

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Student
import com.beleavemebe.solevarnya.databinding.ListItemStudentBinding
import com.beleavemebe.solevarnya.presentation.model.DegreeEnum
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.GenericDiffUtilItemCallback
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
        fun bind(Student: Student) {
            val c = binding.root.context

            Glide.with(c)
                .load(Student.avatarUrl)
                .placeholder(R.drawable.person_placeholder)
                .into(binding.ivAvatar)

            binding.tvNameSurname.text = c.getString(
                R.string.single_space_placeholder,
                Student.name,
                Student.surname
            )
            binding.tvDegreeGroup.text = c.getString(
                R.string.single_space_placeholder,
                c.getString(DegreeEnum.from(Student.degree).stringResId),
                Student.group
            )
            binding.ibMore.setOnClickListener {
                onMoreButtonClicked(binding.ibMore, Student)
            }
            binding.root.setOnClickListener {
                binding.tvDegreeGroup.text = Student.quote
            }
        }
    }

    companion object {
        private val studentDiffCallback = GenericDiffUtilItemCallback<Student>()
    }
}
