package com.fuf.colorio.ui.screens.generator

import android.util.Log
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fuf.colorio.ui.utils.extensions.RandomExtensions.nextColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

data class GeneratorScreenState(
    val colors: List<PaletteColor> = List(5) { PaletteColor() },
    val colorModels: List<String> = listOf("m1", "m2"),
    val selectedColorModelIndex: Int = 0,
    val showColorModelsList: Boolean = false,
    val showColorPicker: Boolean = false
) {
    val selectedColorModel get() = colorModels[selectedColorModelIndex]
}

@Immutable
data class PaletteColor(
    val value: Color = Color.Unspecified,
    val isLocked: Boolean = false
)

class GeneratorScreenViewModel : ViewModel() {
    private var changingColorIndex: Int = -1
    private var _colors: MutableList<PaletteColor> = MutableList(5) { PaletteColor() }

    private var _uiState = MutableStateFlow(GeneratorScreenState(colors = _colors))
    val uiState = _uiState.asStateFlow()

    fun generateNewColors() = viewModelScope.launch {
        withContext(Dispatchers.Main) {
            Log.d("generate new colors", "generating colors...")
        }

        _colors = _colors.map { it.copy(value = Random.nextColor()) }.toMutableList()
        _uiState.tryEmit(_uiState.value.copy(colors = _colors))
    }

    fun refreshColorModels() = viewModelScope.launch {
        _uiState.tryEmit(_uiState.value.copy(colorModels = (1..4).map { Random.nextBytes(5).toString() }))
    }

    fun selectColorModel(index: Int) = viewModelScope.launch {
        _uiState.tryEmit(_uiState.value.copy(selectedColorModelIndex = index))
    }

    fun onColorLockToggle(@IntRange(0, 4) colorIndex: Int, isLocked: Boolean) {
        _colors[colorIndex] = _uiState.value.colors[colorIndex].copy(isLocked = isLocked)
        _uiState.tryEmit(_uiState.value.copy(colors = _colors))
    }

    fun showColorModelsListDialog() {
        _uiState.tryEmit(_uiState.value.copy(showColorModelsList = true))
    }

    fun closeColorModelsListDialog() {
        _uiState.tryEmit(_uiState.value.copy(showColorModelsList = false))
    }

    fun showColorPickerDialog(@IntRange(0, 4) colorIndex: Int) {
        changingColorIndex = colorIndex
        _uiState.tryEmit(_uiState.value.copy(showColorPicker = true))
    }

    fun applySelectedColor(color: Color) {
        _uiState.tryEmit(
            _uiState.value.copy(
                colors = _colors.also {
                    it[changingColorIndex] = _colors[changingColorIndex].copy(value = color)
                    changingColorIndex = -1
                },
                showColorPicker = false
            )
        )
    }

    fun closeColorPickerDialog() {
        changingColorIndex = -1
        _uiState.tryEmit(_uiState.value.copy(showColorPicker = false))
    }
}