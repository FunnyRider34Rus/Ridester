package com.funnyrider34rus.ridester.core.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RidesterOutlinedTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    focusManager: FocusManager,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    singleLine: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = stringResource(id = label),
                style = MaterialTheme.typography.titleMedium
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        singleLine = singleLine,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f),
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        )
    )
}