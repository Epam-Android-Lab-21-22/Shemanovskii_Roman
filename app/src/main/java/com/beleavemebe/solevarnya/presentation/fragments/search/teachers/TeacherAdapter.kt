package com.beleavemebe.solevarnya.presentation.fragments.search.teachers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Teacher
import com.beleavemebe.solevarnya.databinding.ListItemAddTeacherBinding
import com.beleavemebe.solevarnya.databinding.ListItemTeacherBinding
import com.beleavemebe.solevarnya.framework.model.AcademicRankResourceEnum
import com.beleavemebe.solevarnya.framework.model.CampusLocationResourceEnum
import com.beleavemebe.solevarnya.presentation.fragments.search.GenericDiffUtilItemCallback
import com.beleavemebe.solevarnya.framework.util.illegalArgument
import com.bumptech.glide.Glide

class TeacherAdapter(
    private val onAddTeacherItemClicked: () -> Unit
) : ListAdapter<Teacher, RecyclerView.ViewHolder>(teacherDiffCallback) {
    enum class TeacherViewType {
        TEACHER,
        ADD_TEACHER,
    }

    override fun getItemCount(): Int =
        super.getItemCount() + 1

    override fun getItemViewType(position: Int): Int =
        when (position) {
            itemCount - 1 -> TeacherViewType.ADD_TEACHER.ordinal
            else -> TeacherViewType.TEACHER.ordinal
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TeacherViewType.ADD_TEACHER.ordinal -> createAddTeacherViewHolder(inflater, parent)
            TeacherViewType.TEACHER.ordinal -> createTeacherViewHolder(inflater, parent)
            else -> illegalArgument("Unknown view type $viewType")
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

    private fun createTeacherViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): TeacherViewHolder {
        val binding = ListItemTeacherBinding.inflate(inflater, parent, false)
        return TeacherViewHolder(binding)
    }

    class TeacherViewHolder(
        private val binding: ListItemTeacherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) {
            val c = binding.root.context

            Glide.with(c)
                .load(teacher.avatarUrl)
                .placeholder(R.drawable.person_placeholder)
                .into(binding.ivAvatar)

            binding.tvNameSurname.text =
                c.getString(R.string.single_space_placeholder, teacher.name, teacher.surname)
            binding.tvRank.text =
                c.getString(AcademicRankResourceEnum.from(teacher.rank).stringResId)
            binding.tvLocation.text =
                c.getString(CampusLocationResourceEnum.from(teacher.location).stringResId)
        }
    }

    private fun createAddTeacherViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): AddTeacherViewHolder {
        val binding = ListItemAddTeacherBinding.inflate(inflater, parent, false)
        return AddTeacherViewHolder(binding)
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

    companion object {
        private val teacherDiffCallback = GenericDiffUtilItemCallback<Teacher>()
    }
}

