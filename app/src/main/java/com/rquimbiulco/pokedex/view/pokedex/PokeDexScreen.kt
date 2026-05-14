package com.rquimbiulco.pokedex.view.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.view.auth.register.BottomBar
import com.rquimbiulco.pokedex.view.auth.register.TopBar
import com.rquimbiulco.pokedex.view.core.components.PokeText

@Composable
fun PokedexScreen(
    pokeDexViewModel: PokeDexViewModel = hiltViewModel(),
) {
    val lazyPokemonItems = pokeDexViewModel.pokemonFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            PokeText(
                text = stringResource(R.string.pokedex_screen_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(24.dp)
            )
        }
    ) { padding ->
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxSize(),
        ) {
            LazyColumn {
                items(count = lazyPokemonItems.itemCount) { index ->
                    val pokemon = lazyPokemonItems[index]
                    pokemon?.let {
                        PokemonItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemonModel: PokemonModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pokemonModel.imageUrl,
                contentDescription = "Imagen de ${pokemonModel.name}",
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
fun PokedexScreenPreview() {
    PokedexScreen()
}