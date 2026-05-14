package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun MyFAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    contentDescription: String,
    icon: Painter,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(12.dp),
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}