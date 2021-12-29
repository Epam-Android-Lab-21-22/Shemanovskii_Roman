package com.beleavemebe.solevarnya.ui.fragments.search.subjects

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.beleavemebe.solevarnya.databinding.ListItemSubjectBinding
import com.beleavemebe.solevarnya.model.Subject
import com.beleavemebe.solevarnya.ui.fragments.search.GenericDiffUtilItemCallback

class SubjectsAdapter : ListAdapter<Subject, SubjectViewHolder>(subjectDiffCallback) {
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
}
