package com.example.noteapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.db.AppDatabace
import com.example.noteapp.utils.Preferens

class App : Application() {

    companion object{
        var appDatabase: AppDatabace? = null
    }
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Preferens()
        sharedPreferences.unit(this)
        getInstance()
    }

    private fun getInstance(): AppDatabace? {
        if (appDatabase == null){
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(
                    context,
                    AppDatabace::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}