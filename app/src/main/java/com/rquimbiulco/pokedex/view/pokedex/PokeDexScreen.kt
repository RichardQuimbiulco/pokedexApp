package com.rquimbiulco.pokedex.view.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.view.core.components.PokeModalNavigationDrawer
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScreen(
    onAction: (PokedexAction) -> Unit,
    pokemonItems: LazyPagingItems<PokemonModel>,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    PokeModalNavigationDrawer(drawerState, onItemClick = { item ->
        onAction(
            PokedexAction.DrawerItemClicked(destination = item)
        )
    }) {
        Scaffold(
            topBar = {
                TopBar(
                    title = {
                        PokeText(
                            text = stringResource(R.string.pokedex_screen_title),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(24.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_menu),
                                contentDescription = null
                            )
                        }
                    }
                )

            },
        ) { padding ->
            LazyColumn(
                Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(padding)
                    .padding(start = 14.dp, end = 14.dp)
                    .fillMaxSize(),
            ) {
                items(count = pokemonItems.itemCount) { index ->
                    val pokemon = pokemonItems[index]
                    pokemon?.let { pokemon ->
                        PokemonItem(pokemon) { onAction(PokedexAction.PokemonClicked(pokemon.id)) }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemonModel: PokemonModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pokemonModel.imageUrl,
                contentDescription = stringResource(
                    R.string.image_content_description,
                    pokemonModel.name
                ),
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            PokeText(
                text = pokemonModel.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokemonItem(
        pokemonModel = PokemonModel(
            id = 0,
            name = "bulbasaur",
            imageUrl = ""
        ),
        onClick = {}
    )
}