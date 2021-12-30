package com.beleavemebe.solevarnya.ui.fragments.search.teachers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemAddTeacherBinding
import com.beleavemebe.solevarnya.databinding.ListItemTeacherBinding
import com.beleavemebe.solevarnya.model.Teacher
import com.beleavemebe.solevarnya.ui.fragments.search.GenericDiffUtilItemCallback
import java.lang.IllegalArgumentException

class TeacherAdapter(
    private val onAddTeacherItemClicked: () -> Unit
) : ListAdapter<Teacher, RecyclerView.ViewHolder>(teacherDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TeacherViewType.ADD_TEACHER.ordinal -> createAddTeacherViewHolder(inflater, parent)
            TeacherViewType.TEACHER.ordinal -> createTeacherViewHolder(inflater, parent)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            position < itemCount - 1 && holder is TeacherViewHolder -> {
                val item = getItem(position)
                holder.bind(item)
            }
            holder is AddTeacherViewHolder -> {
                holder.bind(onAddTeacherItemClicked)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            itemCount - 1 -> TeacherViewType.ADD_TEACHER
            else -> TeacherViewType.TEACHER
        }.ordinal

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    private fun createAddTeacherViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AddTeacherViewHolder {
        val binding = ListItemAddTeacherBinding.inflate(inflater, parent, false)
        return AddTeacherViewHolder(binding)
    }

    private fun createTeacherViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TeacherViewHolder {
        val binding = ListItemTeacherBinding.inflate(inflater, parent, false)
        return TeacherViewHolder(binding)
    }

    companion object {
        private val teacherDiffCallback = GenericDiffUtilItemCallback<Teacher>()
    }

    enum class TeacherViewType {
        TEACHER,
        ADD_TEACHER,
    }

    class AddTeacherViewHolder(
        private val binding: ListItemAddTeacherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onAddTeacherItemClicked: () -> Unit) {
            binding.root.setOnClickListener {
                onAddTeacherItemClicked()
            }
        }
    }

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
}

