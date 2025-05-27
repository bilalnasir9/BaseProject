package com.example.baseproject.room.noteappexample

import androidx.lifecycle.LiveData
import com.example.baseproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : BaseViewModel() {
    val notes: LiveData<List<Note>> = repository.notes

    fun insert(note: Note) = launchOnMain {
        repository.insert(note)
    }

    fun update(note: Note) = launchOnMain {
        repository.update(note)
    }

    fun delete(note: Note) = launchOnMain {
        repository.delete(note)
    }
}