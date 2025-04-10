package com.example.noteapp.model.models

import java.io.Serializable

data class NoteAppModel(
    val animation: Int,
    val title: String,
    val desc: String
) : Serializable