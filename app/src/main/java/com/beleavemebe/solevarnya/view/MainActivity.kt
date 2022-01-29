package com.beleavemebe.solevarnya.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListUpdateCallback
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beleavemebe.solevarnya.R
import com.beleavemebe.solevarnya.databinding.ActivityMainBinding
import com.beleavemebe.solevarnya.di.ServiceLocator

class MainActivity : AppCompatActivity(R.layout.activity_main), ListUpdateCallback {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: IMainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val noteAdapter = NoteAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        ServiceLocator.init(application)
        super.onCreate(savedInstanceState)
        initDropdownOptions()
        initListeners()
        initRecyclerView()
        viewModel.state.observe(this, ::renderUi)
    }

    private fun initListeners() {
        binding.btnNewNote.setOnClickListener {
            viewModel.onAddNewNote()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this).apply {
                stackFromEnd = true
            }
        binding.recyclerView.adapter = noteAdapter
    }

    private fun renderUi(state: State) {
        when (state) {
            is State.NoDataSourceSelected -> {
                binding.progressCircle.isVisible = false
                binding.recyclerView.isVisible = false
                binding.btnNewNote.isEnabled = false
                binding.tvCenter.text = getString(R.string.choose_data_source)
            }
            is State.Loading -> {
                binding.recyclerView.isVisible = false
                binding.tvCenter.isVisible = false
                binding.btnNewNote.isEnabled = false
                binding.progressCircle.isVisible = true
            }
            is State.Error -> {
                binding.progressCircle.isVisible = false
                binding.recyclerView.isVisible = false
                binding.tvCenter.isVisible = true
                binding.tvCenter.text = when (state.exception) {
                    is NoteLoadingException.DataSourceUnavailable ->
                        getString(R.string.source_unavailable)
                }
            }
            is State.DisplayingNotes -> {
                binding.progressCircle.isVisible = false
                binding.tvCenter.isVisible = false
                binding.recyclerView.isVisible = true
                binding.btnNewNote.isEnabled = true
                noteAdapter.submitNotes(state.notes)
                binding.recyclerView.scrollToPosition(state.notes.lastIndex)
            }
        }
    }

    private fun initDropdownOptions() {
        val optionsArray = DATA_SOURCES
        val stringArray = optionsArray.map { getString(it.titleResId) }
        val adapter = ArrayAdapter(this, R.layout.dropdown_menu_item, stringArray)
        (binding.tiNoteSource.editText as? AutoCompleteTextView)
            ?.apply {
                setAdapter(adapter)
                setOnItemClickListener { _, _, position, _ ->
                    val selectedDataSource = optionsArray[position]
                    viewModel.onDataSourceChanged(selectedDataSource)
                }
            }
    }

    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}

    override fun onChanged(position: Int, count: Int, payload: Any?) {
        binding.recyclerView.scrollToPosition(noteAdapter.itemCount)
    }
}
