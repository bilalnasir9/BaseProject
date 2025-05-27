package com.example.baseproject.global

import android.content.Context
import androidx.room.Room
import com.example.baseproject.room.AppDatabase
import com.example.baseproject.room.noteappexample.NoteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "note_db").build()
    }

    @Provides
    fun provideNoteDAO(db: AppDatabase): NoteDAO {
        return db.noteDao()
    }
}