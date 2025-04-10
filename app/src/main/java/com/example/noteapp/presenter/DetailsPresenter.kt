package com.example.noteapp.presenter

import android.util.Log
import com.example.noteapp.App
import com.example.noteapp.model.data.models.NoteModel

class DetailsPresenter(

    val view: DetailContract.View

): DetailContract.Presenter {
    override fun loadNote(noteID: Int){
        view.showNote(App.appDatabase?.noteDao()?.getByID(noteID) ?: NoteModel("", "", ""))
    }
    override fun saveNote(note:NoteModel) {
        Log.d("TAG", "saveNote: ${note}")
        App.appDatabase?.noteDao()?.insertNote(note)
    }

    override fun updateNote(note:NoteModel) {
        Log.d("TAG", "replceNote: ${note}")
        App.appDatabase?.noteDao()?.replaceNote(note)
    }
}