package com.machimbira.currency.persistence.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.machimbira.currency.persistence.R

class PreferencesManager(val context: Context) {
    private val KEY_LANGUAGE_PREFERENCE = "languagePreference"

    private var prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


    private var editor: SharedPreferences.Editor


    fun setLanguagePreference(language: String) {
        editor.putString(KEY_LANGUAGE_PREFERENCE, language)
        editor.commit()
    }

    fun getLanguagePreference(): String {
        return prefs.getString(KEY_LANGUAGE_PREFERENCE, context.resources.getString(R.string.english))
    }


    init {
        editor = prefs.edit()
    }

    fun setLanguagePreferenceToEnglish() {
        editor.putString(KEY_LANGUAGE_PREFERENCE,  context.resources.getString(R.string.english))
        editor.commit()
    }
    fun setLanguagePreferenceToShona() {
        editor.putString(KEY_LANGUAGE_PREFERENCE,  context.resources.getString(R.string.shona))
        editor.commit()
    }
}