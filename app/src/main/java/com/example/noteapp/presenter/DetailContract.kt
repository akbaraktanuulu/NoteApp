package com.example.noteapp.presenter

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.noteapp.model.data.models.NoteModel

interface DetailContract {

    interface View {
        fun showNote(note: NoteModel)
        fun showErrorMessage(message: String)
    }
    interface Presenter {
        fun loadNote(noteID: Int)
        fun saveNote(note: NoteModel)
        fun updateNote(note: NoteModel)
    }
}