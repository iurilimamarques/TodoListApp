package br.edu.satc.todolistcompose.storage

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel

class PreferencesViewModel(application: Application): AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    private val _themeMode = mutableStateOf(loadThemeFromPrefs())
    val themeMode: State<ThemeMode> = _themeMode

    private fun loadThemeFromPrefs(): ThemeMode {
        return when (prefs.getString("theme_mode", "SYSTEM")) {
            "LIGHT" -> ThemeMode.LIGHT
            "DARK" -> ThemeMode.DARK
            else -> ThemeMode.SYSTEM
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
        prefs.edit().putString("theme_mode", mode.name).apply()
    }
}