package com.fuf.colorio.ui.screens.generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Texture
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fuf.colorio.ui._shared.components.ColorBlock
import com.fuf.colorio.ui._shared.components.dialogs.ListDialog
import io.mhssn.colorpicker.ColorPickerDialog

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GeneratorScreen(vm: GeneratorScreenViewModel = viewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        ColorPalette(
            modifier = Modifier.weight(1f),
            colors = state.colors,
            onColorClick = vm::showColorPickerDialog,
            onLockToggle = vm::onColorLockToggle
        )

        ColorPaletteActions(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(0.dp, 10.dp),
            colorModelsText = state.selectedColorModel,
            onColorModelClick = vm::showColorModelsListDialog,
            onGenerateClick = vm::generateNewColors,
            onSaveClick = vm::refreshColorModels
        )
    }

    ListDialog(
        show = state.showColorModelsList,
        title = "Models",
        items = state.colorModels,
        initialIndex = state.selectedColorModelIndex,
        onSelectIndex = vm::selectColorModel,
        onDismiss = vm::closeColorModelsListDialog
    )

    ColorPickerDialog(
        show = state.showColorPicker,
        onDismissRequest = vm::closeColorPickerDialog,
        onPickedColor = vm::applySelectedColor
    )
}

@Composable
fun ColorPalette(
    modifier: Modifier = Modifier,
    colors: List<PaletteColor>,
    onColorClick: (colorIndex: Int) -> Unit,
    onLockToggle: (colorIndex: Int, isChecked: Boolean) -> Unit
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        colors.forEachIndexed { i, color ->
            ColorBlock(
                modifier = Modifier.weight(1f),
                color = color.value,
                locked = color.isLocked,
                onClick = { onColorClick(i) },
                onToggleLock = { onLockToggle(i, !color.isLocked) }
            )
        }
    }
}

@Composable
fun ColorPaletteActions(
    modifier: Modifier = Modifier,
    colorModelsText: String,
    onColorModelClick: () -> Unit,
    onGenerateClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(4.dp),
            onClick = onColorModelClick,
        ) {
            Icon(
                Icons.Rounded.Texture,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.width(ButtonDefaults.IconSpacing))
            Text(colorModelsText)
        }

        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = onGenerateClick,
            content = { Text("Generate") }
        )

        OutlinedButton(
            onClick = onSaveClick,
            shape = RoundedCornerShape(4.dp),
        ) {
            Icon(
                Icons.Rounded.Save,
                contentDescription = "Save",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.width(ButtonDefaults.IconSpacing))
            Text("Save")
        }
    }
}

@Preview(name = "GeneratorScreen")
@Composable
private fun PreviewGeneratorScreen() {
    GeneratorScreen()
}