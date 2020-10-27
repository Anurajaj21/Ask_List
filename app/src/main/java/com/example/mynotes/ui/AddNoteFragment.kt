package com.example.mynotes.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import com.example.mynotes.R
import com.example.mynotes.db.Note
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


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
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toast("Task added")


                }
            }
            Navigation.findNavController(view).navigate(AddNoteFragmentDirections.actionSaveNote())
        }

    }



    }


