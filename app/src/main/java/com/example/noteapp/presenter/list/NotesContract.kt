package com.example.noteapp.presenter.list

import com.example.noteapp.model.data.models.NoteModel

interface NotesContract {

    interface View {
        fun showNotes(notes: List<NoteModel>)
        fun showError(massage: String)
    }

    interface Presenter {
        fun loadNotes()
        fun deleteNote(noteModel: NoteModel)
    }
}