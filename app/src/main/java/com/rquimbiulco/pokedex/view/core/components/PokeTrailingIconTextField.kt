package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PokeTrailingIconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    label: String = "",
    maxLines: Int = 1,
    singleLine: Boolean = false,
    keyboard: KeyboardOptions = KeyboardOptions.Default,
    trailingIconId: Int,
    onIconClick: () -> Unit,
    trailingIconContentDescription: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            PokeText(
                text = label,
            )
        },
        shape = shape,
        maxLines = maxLines,
        singleLine = singleLine,
        keyboardOptions = keyboard,
        trailingIcon = {
            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(trailingIconId),
                    contentDescription = trailingIconContentDescription
                )
            }
        },
        visualTransformation = visualTransformation
    )
}