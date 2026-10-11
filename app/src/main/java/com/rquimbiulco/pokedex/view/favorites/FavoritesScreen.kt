package com.rquimbiulco.pokedex.view.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.TopBar
import com.rquimbiulco.pokedex.view.core.extensions.backgroundColor
import com.rquimbiulco.pokedex.view.core.extensions.iconRes
import com.rquimbiulco.pokedex.view.details.PokemonType
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    state: FavoritesUiState,
    onAction: (FavoritesAction) -> Unit,
    pokemonItems: LazyPagingItems<PokemonModel>
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBar(
                title = {
                    PokeText(
                        text = stringResource(R.string.favorites_screen_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
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
        LazyVerticalGrid(
            modifier = Modifier
                .padding(padding)
                .padding(start = 14.dp, end = 14.dp)
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(count = pokemonItems.itemCount) { index ->
                val pokemon = pokemonItems[index]
                pokemon?.let { pokemon ->
                    FavoritePokemonItem(pokemon) { onAction(FavoritesAction.PokemonClicked(pokemon.id)) }
                }
            }
        }
    }
}

@Composable
fun FavoritePokemonItem(pokemonModel: PokemonModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clipToBounds(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = 70.dp)
                        .background(
                            color = pokemonModel.types.first().backgroundColor()
                                .copy(alpha = 0.35f),
                            shape = CircleShape
                        )
                )

                AsyncImage(
                    model = pokemonModel.imageUrl,
                    contentDescription = stringResource(
                        R.string.image_content_description,
                        pokemonModel.name
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .aspectRatio(1f)
                )

                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = null
                    )
                }
            }

            PokeText(
                text = pokemonModel.name.replaceFirstChar {
                    it.uppercase()
                },
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(2.dp))

            PokeText(
                text = "#${pokemonModel.id.toString().padStart(3, '0')}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            PokemonTypeChip(
                type = pokemonModel.types.first()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PokemonTypeChip(
    type: PokemonType
) {
    Surface(
        color = type.backgroundColor(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(type.iconRes()),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color.Unspecified
            )

            PokeText(
                text = type.name,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    FavoritePokemonItem(
        pokemonModel = PokemonModel(
            id = 0,
            name = "Bulbasaur",
            imageUrl = "",
            types = listOf(PokemonType.POISON)
        ),
        onClick = {}
    )
}
