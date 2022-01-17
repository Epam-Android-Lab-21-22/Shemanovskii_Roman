package com.beleavemebe.solevarnya.presentation.ui.fragments.search.subjects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.core.domain.Subject
import com.beleavemebe.solevarnya.databinding.ListItemSubjectBinding
import com.beleavemebe.solevarnya.presentation.ui.fragments.search.GenericDiffUtilItemCallback
import com.bumptech.glide.Glide

class SubjectsAdapter : ListAdapter<Subject, SubjectsAdapter.SubjectViewHolder>(subjectDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSubjectBinding.inflate(inflater, parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SubjectViewHolder(
        private val binding: ListItemSubjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject) {
            val c = binding.root.context

            Glide.with(c)
                .load(subject.imgUrl)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivBackground)

            binding.tvSubjectTitle.text =
                subject.name
            binding.tvSubjectCredits.text =
                c.getString(R.string.credits_placeholder, subject.credits)
            binding.tvSubjectEnrolled.text =
                c.getString(R.string.enrolled_placeholder, subject.enrolled)
        }
    }

    companion object {
        private val subjectDiffCallback = GenericDiffUtilItemCallback<Subject>()
    }
}

