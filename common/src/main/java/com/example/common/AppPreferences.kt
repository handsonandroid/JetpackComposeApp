package com.example.common

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val PREF_NAME = "my_app_prefs"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_LOGGED_IN = "logged_in"

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isFirstLaunch(context: Context): Boolean =
        prefs(context).getBoolean(KEY_FIRST_LAUNCH, true)

    fun setFirstLaunch(context: Context, value: Boolean) {
        prefs(context).edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()
    }

    fun isLoggedIn(context: Context): Boolean =
        prefs(context).getBoolean(KEY_LOGGED_IN, false)

    fun setLoggedIn(context: Context, value: Boolean) {
        prefs(context).edit().putBoolean(KEY_LOGGED_IN, value).apply()
    }
}