package com.example.noteapp

import android.app.Application
import com.example.noteapp.utils.Preferens

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Preferens()
        sharedPreferences.unit(this)
    }
}