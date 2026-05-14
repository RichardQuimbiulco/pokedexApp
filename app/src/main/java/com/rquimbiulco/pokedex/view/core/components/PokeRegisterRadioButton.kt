package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MyPokeRegisterRadioButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String,
    text: String,
    onClick: (String) -> Unit,
    selected: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Column(
        modifier = modifier.clickable { onClick(text) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(75.dp),
            painter = icon,
            contentDescription = contentDescription
        )
        Spacer(Modifier.height(25.dp))
        RadioButton(
            selected = selected,
            onClick = { onClick(text) }
        )
        PokeText(
            text = text,
            color = color
        )
    }
}