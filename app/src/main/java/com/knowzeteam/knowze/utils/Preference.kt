package com.knowzeteam.knowze.utils

import android.content.Context

object Preference {
    private const val PREF_NAME = "onSignIn"
    private const val KEY_TOKEN = "token"

    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TOKEN, null)
    }

    fun saveToken(context: Context, token: String) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun logOut(context: Context) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
    }
}
