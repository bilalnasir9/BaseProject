package com.example.baseproject.room.noteappexample

import androidx.lifecycle.LiveData
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDAO) {
    val notes: LiveData<List<Note>> = noteDao.getAllNotes()

//    suspend fun getAllNotes() =noteDao.getAllNotes()
    suspend fun insert(note: Note) = noteDao.insertNote(note)
    suspend fun update(note: Note) = noteDao.updateNote(note)
    suspend fun delete(note: Note) = noteDao.deleteNote(note)

}