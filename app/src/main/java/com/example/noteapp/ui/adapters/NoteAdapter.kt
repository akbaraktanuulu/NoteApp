package com.example.noteapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.NoteHolderBinding

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding : NoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note : NoteModel) = with(binding) {
            titleTextView.text = note.title
            textTextView.text = note.description
            dateTextView.text = note.date
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<NoteModel>(){
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return newItem == oldItem
    }
}
