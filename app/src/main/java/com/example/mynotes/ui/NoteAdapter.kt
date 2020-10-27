package com.example.mynotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.db.Note
import kotlinx.android.synthetic.main.note_layout.view.*

class NoteAdapter(val notes: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {




    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout,parent,false)
        )
    }


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.view.text_note_title.text = notes[position].title
        holder.view.text_note_description.text = notes[position].note


    }

    override fun getItemCount() = notes.size
}