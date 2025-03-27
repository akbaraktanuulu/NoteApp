package com.example.noteapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp.data.models.NoteModel


@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNote(noteModel: NoteModel)
    @Query("SELECT * FROM model")
    abstract fun getAll(): LiveData<List<NoteModel>>
}