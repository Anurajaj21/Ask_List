package com.example.mynotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mynotes.R
import com.example.mynotes.db.Note
import com.example.mynotes.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    private var argtask: Note? = null
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

        list.setOnClickListener{
            val action2 = HomeFragmentDirections.actionHomeFragmentToNoteList()
            Navigation.findNavController(it).navigate(action2)
        }
            launch{
                context?.let {
                    argtask = NoteDatabase(it).getNoteDao().getlatestNote()
                }
                if(argtask != null) {
                    latest_task_title.text = argtask?.title
                    latest_task_desc.text = argtask?.note
                    total.text = argtask?.id.toString()
                }
            }
//        if(argtask != null)
//            latest_task_title.text = argtask?.title

//        recycler_view_notes.setHasFixedSize(true)
//        recycler_view_notes.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        launch {
//            context?.let {
//                val notes = NoteDatabase(it).getNoteDao().getAllNote()
//                recycler_view_notes.adapter = NoteAdapter(notes)
//            }
//        }

    }

}