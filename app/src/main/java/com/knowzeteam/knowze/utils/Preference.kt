package com.knowzeteam.knowze.utils

import android.content.Context
import kotlin.properties.Delegates

object Preference {
    private const val PREF_NAME = "onSignIn"
    private const val KEY_TOKEN = "token"

    // Delegate to observe token changes
    private var token: String? by Delegates.observable(null) { _, _, _ ->
        onTokenChanged?.invoke(token)
    }

    // Callback to be invoked when token changes
    var onTokenChanged: ((String?) -> Unit)? = null

    fun getToken(context: Context): String? {
        if (token == null) {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            token = prefs.getString(KEY_TOKEN, null)
        }
        return token
    }

    fun saveToken(context: Context, newToken: String) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_TOKEN, newToken)
        editor.apply()
        token = newToken
    }

    fun logOut(context: Context) {
        val editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
        token = null
    }
}
