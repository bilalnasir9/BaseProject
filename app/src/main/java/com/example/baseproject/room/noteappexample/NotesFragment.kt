package com.example.baseproject.room.noteappexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.baseproject.base.showToast
import com.example.baseproject.databinding.FragmentnotesBinding
import com.example.baseproject.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment<FragmentnotesBinding>() {

    private val viewModel: NoteViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentnotesBinding {
        return FragmentnotesBinding.inflate(inflater, container, false)

    }
    override fun setupViews() {

        adapter = NoteAdapter(
            onEdit = { note -> viewModel.update(
                note.copy(title = "${note.title}_Updated", content = "${note.content}_Updated")
            ) },
            onDelete = { note -> viewModel.delete(note) }
        )
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val note = Note(title = "Title_${System.currentTimeMillis()}", content = "Content_${System.currentTimeMillis()}")
            viewModel.insert(note)
        }
    }
    override fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let { it1 -> requireContext().showToast(it1) }
        }
    }


}