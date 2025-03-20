package com.example.noteapp.models

import java.io.Serializable

data class NoteAppModel(
    val animation: Int,
    val title: String,
    val desc: String
) : Serializable