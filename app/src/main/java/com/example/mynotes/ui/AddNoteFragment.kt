package com.example.mynotes.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mynotes.R
import com.example.mynotes.db.Note
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AddNoteFragment : BaseFragment() {

    private var argnote: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            argnote = AddNoteFragmentArgs.fromBundle(it).note
            title.setText(argnote?.title)
            note.setText(argnote?.note)
        }
        //NoteDatabase(requireActivity()).getNoteDao().
        button_save.setOnClickListener setOnClickLister@{ view -> view
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
            launch {

                context?.let{
                    val mNote = Note(noteTitle, noteBody)
                    if(argnote == null){
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Note Saved")
                    }
                    else{
                        mNote.id = argnote!!.id
                        NoteDatabase(it).getNoteDao().update(mNote)
                        it.toast("Note Updated")
                    }


                }
            }
            Navigation.findNavController(view).navigate(AddNoteFragmentDirections.actionSaveNote())
        }

         }

}