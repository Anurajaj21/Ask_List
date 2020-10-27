package com.example.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotes.R
import com.example.mynotes.db.Note
import com.example.mynotes.db.NoteDao
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_note_list.*
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class note_list : BaseFragment() {
    // TODO: Rename and change types of parameters
    var noteAdapter: NoteAdapter? = null
    var notes: ArrayList<Note> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_notes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        noteAdapter = NoteAdapter(notes)
        recyclerView.adapter = noteAdapter
        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        launch {
            context?.let {
                notes.addAll(NoteDatabase(it).getNoteDao().getAllNote())
                noteAdapter?.notifyDataSetChanged()
            }
        }
    }
}
