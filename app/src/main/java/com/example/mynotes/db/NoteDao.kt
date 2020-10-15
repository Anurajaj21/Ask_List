package com.example.mynotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note : Note)

    @Query("SELECT* FROM Note")
    suspend fun getAllNote() : List<Note>

    @Insert
    suspend fun addMultipleNote(vararg note: Note)
}