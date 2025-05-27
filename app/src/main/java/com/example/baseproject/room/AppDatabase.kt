package com.example.baseproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseproject.room.noteappexample.Note
import com.example.baseproject.room.noteappexample.NoteDAO

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun noteDao(): NoteDAO
}