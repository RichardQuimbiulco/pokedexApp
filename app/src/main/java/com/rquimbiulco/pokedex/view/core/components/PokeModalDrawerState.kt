package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
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
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination
import com.rquimbiulco.pokedex.view.core.components.model.DrawerItem

@Composable
fun PokeModalNavigationDrawer(
    drawerState: DrawerState,
    items: List<DrawerItem>,
    onItemClick: (DrawerItem) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokeDrawerContent(myItems = items, onItemClick = onItemClick)
        }
    ) {
        content()
    }
}

@Composable
fun PokeDrawerContent(myItems: List<DrawerItem>, onItemClick: (DrawerItem) -> Unit) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
        drawerContentColor = Color.White,
    ) {
        Spacer(Modifier.height(24.dp))
        myItems.forEachIndexed { index, item ->
            if (item.id == DrawerDestination.Logout) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }
            NavigationDrawerItem(
                label = { stringResource(item.title) },
                selected = selectedIndex == index,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.contentDescription
                    )
                },
                badge = {
                    if (item.notification > 0) {
                        Badge(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ) { Text(item.notification.toString()) }
                    }
                },
                shape = RoundedCornerShape(0)
            )
        }
    }
}