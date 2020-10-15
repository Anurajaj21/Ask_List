package com.example.mynotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotes.R
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_save.setOnClickListener {
            val action = HomeFragmentDirections.actionaddnote()
            Navigation.findNavController(it).navigate(action)
        }
        recycler_view_notes.setHasFixedSize(true)
        recycler_view_notes.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        launch {
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNote()
                recycler_view_notes.adapter = NoteAdapter(notes)
            }
        }
    }
}