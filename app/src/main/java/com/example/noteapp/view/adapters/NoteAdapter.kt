package com.example.noteapp.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.model.data.models.NoteModel
import com.example.noteapp.databinding.GridNoteHolderBinding
import com.example.noteapp.databinding.NoteHolderBinding
import com.example.noteapp.view.inter.OnClickItem

class NoteAdapter(
    private val onClick: OnClickItem
) : ListAdapter<NoteModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private var isL = true

    fun setLayoutMode(isList: Boolean) {
        isL = isList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (isL) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            ViewHolder(NoteHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            GridViewHolder(GridNoteHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is ViewHolder -> holder.bind(getItem(position))
            is GridViewHolder -> holder.bind(getItem(position))
        }
        holder.itemView.setOnLongClickListener{
            onClick.onLongClick(getItem(position))
            true
        }
        holder.itemView.setOnClickListener {
            onClick.onClick(getItem(position))
        }
    }

    class ViewHolder(private val binding : NoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note : NoteModel) = with(binding) {
            titleTextView.text = note.title
            textTextView.text = note.description
            dateTextView.text = note.date
        }
    }
    class GridViewHolder(private val binding : GridNoteHolderBinding) : RecyclerView.ViewHolder(binding.root) {
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
