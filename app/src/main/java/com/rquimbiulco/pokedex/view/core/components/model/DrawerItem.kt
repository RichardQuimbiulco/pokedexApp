package com.rquimbiulco.pokedex.view.core.components.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination

data class DrawerItem(
    val id: DrawerDestination,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val notification: Int,
    val contentDescription: String
)