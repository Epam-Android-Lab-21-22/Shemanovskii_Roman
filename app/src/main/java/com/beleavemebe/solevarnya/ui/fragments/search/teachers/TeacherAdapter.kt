package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.beleavemebe.solevarnya.databinding.ListItemTeacherBinding
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.ui.fragments.search.GenericDiffUtilItemCallback

class TeacherAdapter : ListAdapter<Teacher, TeacherViewHolder>(teacherDiffCallback) {
    // TODO Implement "add" button via separate view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTeacherBinding.inflate(inflater, parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val teacherDiffCallback = GenericDiffUtilItemCallback<Teacher>()
    }
}
