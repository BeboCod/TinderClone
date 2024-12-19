package com.prueba.tinderswipe.ui.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.prueba.tinderswipe.utils.Constants.DARK_MODE_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val context: Context
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun update(update: HomeState.() -> HomeState) {
        _state.value = _state.value.update()
    }

    init {
        update {
            copy(
                darkMode = context.getSharedPreferences(DARK_MODE_KEY, Context.MODE_PRIVATE)
                    .getBoolean(DARK_MODE_KEY, false)
            )
        }
    }

    fun switchTheme() {
        update {
            copy(
                darkMode = !darkMode
            )
        }
        context.getSharedPreferences(DARK_MODE_KEY, Context.MODE_PRIVATE).edit()
            .putBoolean(DARK_MODE_KEY, _state.value.darkMode).apply()
    }
}