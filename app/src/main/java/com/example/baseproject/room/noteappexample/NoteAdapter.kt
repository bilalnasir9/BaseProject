package com.example.baseproject.room.noteappexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.databinding.ItemnotesBinding

class NoteAdapter(val onEdit: (Note) -> Unit, val onDelete: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.myViewHoldwer>() {
    var listt :List<Note> = ArrayList()

    class myViewHoldwer(val binding: ItemnotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHoldwer {
        val binding = ItemnotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return myViewHoldwer(binding)
    }

    override fun getItemCount(): Int {
        return listt.size
    }
    fun updateList(newList: List<Note>) {
        listt = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: myViewHoldwer, position: Int) {
        val note = listt[position]
        holder.binding.apply {
            tvTitle.text = note.title
            tvContent.text = note.content
            btnEdit.setOnClickListener { onEdit(note) }
            btnDelete.setOnClickListener { onDelete(note) }
        }
    }


}