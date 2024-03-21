package com.example.imc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.imc_screen.ValueState
import com.example.imc.ui.imc_screen.ImcScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeSelector(selectedMode: ImcScreenViewModel.Mode, updateMode: (ImcScreenViewModel.Mode) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        ImcScreenViewModel.Mode.values().forEach {
            ElevatedFilterChip(selectedMode == it, onClick = { updateMode(it) },
                label = {
                    Text(it.name)
                }
            )
        }
    }
}

@Composable
fun RowScope.ActionButton(text: String, onClick: () -> Unit) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = { focusManager.clearFocus(); onClick() },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(14.dp)
    ) {
        Text(text, fontSize = 15.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    state: ValueState,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = state.value,
        onValueChange = onValueChange,
        isError = state.error != null,
        label = {Text(text =  "${state.label}(${state.suffix})")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = imeAction),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}



























