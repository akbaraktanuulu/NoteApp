package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class Preferens {
    private lateinit var sheredPreferens : SharedPreferences
    fun unit(context : Context) {
        sheredPreferens = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }
    var isOneVisit : Boolean
        get() = sheredPreferens.getBoolean("ONE", true)
        set(value) = sheredPreferens.edit().putBoolean("ONE", value).apply()
}