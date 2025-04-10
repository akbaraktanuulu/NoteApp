package com.example.noteapp.view.inter

import com.example.noteapp.model.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
    fun onClick(noteModel: NoteModel)
}