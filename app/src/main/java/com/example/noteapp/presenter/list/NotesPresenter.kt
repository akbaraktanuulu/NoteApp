package com.example.noteapp.presenter.list

import com.example.noteapp.App
import com.example.noteapp.model.data.models.NoteModel

class NotesPresenter(
    val view: NotesContract.View
) : NotesContract.Presenter {

    override fun loadNotes() {
        App.appDatabase?.noteDao()?.getAll()?.observeForever { notes ->
           view.showNotes(notes)
        }
    }

    override fun deleteNote(note: NoteModel) {
        App. appDatabase?.noteDao()?.deleteNote(note)
    }

}