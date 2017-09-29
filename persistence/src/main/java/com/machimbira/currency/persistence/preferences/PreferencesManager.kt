package com.machimbira.currency.persistence.preferences

import android.R.id.edit
import android.app.Application
import android.preference.PreferenceManager
import android.content.SharedPreferences



class PreferencesManager{
    private var prefs: SharedPreferences
    private var editor: SharedPreferences.Editor

    private var instance: PreferencesManager? = null

    fun getInstance(): PreferencesManager {
        if (instance == null) {
            instance = PreferencesManager()
        }

        return instance as PreferencesManager
    }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(Application.getContext())
        editor = prefs.edit()
    }

    fun setStashTapAmount(stashTapAmount: Int) {
        editor.putInt(KEY_STASH_TAP_AMOUNT, stashTapAmount)
        editor.commit()
    }

    fun getStashTapAmount(): Int {
        return prefs.getInt(KEY_STASH_TAP_AMOUNT, 10)
    }

}