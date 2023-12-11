package com.knowzeteam.knowze.utils

import android.content.Context

class UserPreferencesRepository(private val context: Context) {

    fun saveToken(token: String) {
        Preference.saveToken(context, token)
    }

    fun getToken(): String? {
        return Preference.getToken(context)
    }

    fun logOut() {
        Preference.logOut(context)
    }
}
