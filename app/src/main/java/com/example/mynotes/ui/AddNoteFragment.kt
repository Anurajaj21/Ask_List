package com.example.mynotes.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
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
        setHasOptionsMenu(true)
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

    private fun deleteNote(){

        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot get back the deleted note.")
            setPositiveButton("YES"){_,_ ->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(argnote!!)
                    context.toast("Note Deleted")
                }
                Navigation.findNavController(requireView()).navigate(AddNoteFragmentDirections.actionSaveNote())

            }
            setNegativeButton("NO"){_,_ ->

                }
        }.create().show()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote-> if(argnote != null) deleteNote() else context?.toast("Cannot Deleted")
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

}