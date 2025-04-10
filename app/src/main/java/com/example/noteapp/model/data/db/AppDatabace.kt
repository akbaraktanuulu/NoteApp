package com.example.noteapp.model.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.model.data.db.dao.NoteDao
import com.example.noteapp.model.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabace: RoomDatabase() {
    abstract fun noteDao() : NoteDao
}