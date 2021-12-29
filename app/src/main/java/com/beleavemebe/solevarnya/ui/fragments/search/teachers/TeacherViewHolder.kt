package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemTeacherBinding
import com.beleavemebe.solevarnya.model.Teacher

class TeacherViewHolder(
    private val binding: ListItemTeacherBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(teacher: Teacher) {
        val c = binding.root.context
        binding.tvNameSurname.text = c.getString(R.string.single_space_placeholder, teacher.name, teacher.surname)
        binding.tvRank.text = c.getString(teacher.rank.stringResId)
        binding.tvLocation.text = teacher.location
    }
}
