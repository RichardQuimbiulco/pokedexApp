package com.rquimbiulco.pokedex.view.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rquimbiulco.pokedex.R

enum class DrawerDestination(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
) {
    Home(
        title = R.string.drawer_item_home,
        icon = R.drawable.ic_home,
        contentDescription = R.string.content_description_icon_home
    ),
    Favorites(
        title = R.string.drawer_item_favorites,
        icon = R.drawable.ic_favorite,
        contentDescription = R.string.content_description_icon_favourites
    ),
    Settings(
        title = R.string.drawer_item_settings,
        icon = R.drawable.ic_settings,
        contentDescription = R.string.content_description_icon_settings
    ),
    Logout(
        title = R.string.drawer_item_log_out,
        icon = R.drawable.ic_logout,
        contentDescription = R.string.content_description_icon_logout
    ),
}