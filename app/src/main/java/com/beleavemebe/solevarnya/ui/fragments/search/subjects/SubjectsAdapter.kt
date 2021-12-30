package com.beleavemebe.solevarnya.ui.fragments.search.subjects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ListItemSubjectBinding
import com.beleavemebe.solevarnya.model.Subject
import com.beleavemebe.solevarnya.ui.fragments.search.GenericDiffUtilItemCallback

class SubjectsAdapter :
    ListAdapter<Subject, SubjectsAdapter.SubjectViewHolder>(subjectDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSubjectBinding.inflate(inflater, parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val subjectDiffCallback = GenericDiffUtilItemCallback<Subject>()
    }

    class SubjectViewHolder(
        private val binding: ListItemSubjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject) {
            val c = binding.root.context
            binding.tvSubjectTitle.text = subject.name
            binding.tvSubjectCredits.text =
                c.getString(R.string.credits_placeholder, subject.credits)
            binding.tvSubjectEnrolled.text =
                c.getString(R.string.enrolled_placeholder, subject.enrolled)
        }
    }
}

