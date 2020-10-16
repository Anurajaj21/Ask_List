package com.example.mynotes.db

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note : Note)

    @Query("SELECT* FROM Note ORDER BY id DESC")
    suspend fun getAllNote() : List<Note>

    @Insert
    suspend fun addMultipleNote(vararg note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}