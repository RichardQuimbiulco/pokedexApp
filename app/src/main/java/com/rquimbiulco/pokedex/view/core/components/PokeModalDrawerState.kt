package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination

@Composable
fun PokeModalNavigationDrawer(
    drawerState: DrawerState,
    onItemClick: (DrawerDestination) -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokeDrawerContent(onItemClick = onItemClick)
        }
    ) {
        content()
    }
}

@Composable
fun PokeDrawerContent(
    onItemClick: (DrawerDestination) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        drawerContentColor = Color.White,
    ) {
        Spacer(Modifier.height(24.dp))
        DrawerDestination.entries.forEachIndexed { index, item ->
            if (item == DrawerDestination.Logout) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }
            NavigationDrawerItem(
                label = { PokeText(text = stringResource(item.title)) },
                selected = selectedIndex == index,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.contentDescription)
                    )
                },
                shape = RoundedCornerShape(0)
            )
        }
    }
}