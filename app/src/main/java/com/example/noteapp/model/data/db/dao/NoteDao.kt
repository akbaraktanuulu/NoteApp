package com.example.noteapp.model.data.db.dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.data.models.NoteModel


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)
    @Query("SELECT * FROM model")
    fun getAll(): LiveData<List<NoteModel>>
    @Query("SELECT * FROM model WHERE id = :id")
    fun getByID(id : Int): NoteModel?
    @Delete
    fun deleteNote(noteModel: NoteModel)
    @Update
    fun replaceNote(noteModel: NoteModel)
}