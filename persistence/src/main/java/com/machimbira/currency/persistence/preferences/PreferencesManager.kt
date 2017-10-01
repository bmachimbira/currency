package com.machimbira.currency.persistence.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesManager(val context: Context){
    private var prefs: SharedPreferences
    private var editor: SharedPreferences.Editor

    private var instance: PreferencesManager? = null

    fun getInstance(): PreferencesManager {
        if (instance == null) {
            instance = this
        }

        return instance as PreferencesManager
    }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        editor = prefs.edit()
    }

}