package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PokeDropDownMenuItem(modifier: Modifier = Modifier, onClick: () -> Unit, text: String) {
    DropdownMenuItem(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface),
        text = { Text(text = text) },
        onClick = onClick,
    )
}