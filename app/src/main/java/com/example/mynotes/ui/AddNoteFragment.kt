package com.example.mynotes.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynotes.R
import com.example.mynotes.db.Note
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AddNoteFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //NoteDatabase(requireActivity()).getNoteDao().
        button_save.setOnClickListener setOnClickLister@{
             val noteTitle = title.text.toString().trim()
             val noteBody = note.text.toString().trim()

             if(noteTitle.isEmpty()) {
                 title.error = "Title Required"
                 title.requestFocus()
                 return@setOnClickLister
             }
             if(noteBody.isEmpty()) {
                 note.error = "Note Required"
                 note.requestFocus()
                 return@setOnClickLister
             }
            MainScope().launch {
                val note = Note(noteTitle, noteBody)

                context?.let{
                    Log.i("AddNoteFragment", "called2")

                    NoteDatabase(it).getNoteDao().addNote(note)
                    it.toast("Note Saved")
                    Log.i("AddNoteFragment", "called3")

                }
            }

                 //NoteDatabase(requireActivity()).getNoteDao().addNote(note)
             }

         }

}