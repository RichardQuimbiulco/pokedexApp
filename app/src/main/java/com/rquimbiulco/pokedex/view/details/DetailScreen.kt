package com.rquimbiulco.pokedex.view.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.AbilityModel
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.StatModel
import com.rquimbiulco.pokedex.view.core.components.BackButton
import com.rquimbiulco.pokedex.view.core.components.PokeAbilitiesCard
import com.rquimbiulco.pokedex.view.core.components.PokeInfoCard
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.TopBar
import com.rquimbiulco.pokedex.view.core.extensions.backgroundColor
import com.rquimbiulco.pokedex.view.core.extensions.iconRes
import java.util.Locale.getDefault

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    state: DetailUiState,
    onAction: (DetailAction) -> Unit,
) {
    val listState = rememberLazyListState()
    val titleVisibility by remember {
        derivedStateOf {
            when {
                listState.firstVisibleItemIndex > 0 -> 1f
                else -> (listState.firstVisibleItemScrollOffset / 300f)
                    .coerceIn(0f, 1f)
            }
        }
    }
    val backgroundColor = state.pokemonDetail.types
        .firstOrNull()
        ?.backgroundColor()
        ?: MaterialTheme.colorScheme.background

    Scaffold(
        topBar = {
            TopBar(
                title = {
                    PokeText(
                        text = state.pokemonDetail.name.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .alpha(titleVisibility)
                            .offset(
                                x = ((1f - titleVisibility) * 20).dp
                            )
                    )

                },
                navigationIcon = { BackButton { onAction(DetailAction.OnBackButtonClicked) } },
                actions = {
                    IconButton(
                        onClick = {
                            onAction(DetailAction.OnFavoriteClicked(state.isFavorite))
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_favorite),
                            contentDescription = stringResource(R.string.detail_screen_content_description_icon_favorite)
                        )
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                PokemonHeader(pokemon = state.pokemonDetail)
            }

            item {
                PokemonInfo(pokemon = state.pokemonDetail, backgroundColor = backgroundColor)
            }

            item {
                PokemonStats(stats = state.pokemonDetail.stats)
            }
        }
    }
}

@Composable
fun PokemonHeader(pokemon: PokemonDetailModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokeText(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineLarge
        )
        PokeText(
            text = stringResource(
                R.string.detail_screen_pokemon_order,
                pokemon.id.toString().padStart(3, '0')
            ),
            style = MaterialTheme.typography.labelLarge
        )
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.size(200.dp)
        )
        PokemonType(types = pokemon.types)
    }
}

@Composable
fun PokemonType(types: List<PokemonType>) {
    types.forEach { type ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(type.iconRes()),
                    contentDescription = type.value,
                    tint = Color.Unspecified
                )
                PokeText(
                    text = type.name.lowercase(getDefault()).replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PokemonInfo(pokemon: PokemonDetailModel, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        PokeText(
            text = stringResource(R.string.detail_screen_information),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PokeInfoCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.detail_screen_height),
                value = stringResource(
                    R.string.detail_screen_height_value,
                    pokemon.height / 10f
                ),
                accentColor = backgroundColor,
                icon = R.drawable.ic_horizontal_rule
            )
            Spacer(modifier = Modifier.width(16.dp))
            PokeInfoCard(
                modifier = Modifier.weight(1f),
                title = stringResource(R.string.detail_screen_weight),
                value = stringResource(
                    R.string.detail_screen_weight_value,
                    pokemon.weight / 10f
                ),
                accentColor = backgroundColor,
                icon = R.drawable.ic_balance
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PokeAbilitiesCard(
            abilities = pokemon.abilities,
            accentColor = backgroundColor
        )
    }
}

@Composable
fun PokemonStats(
    stats: List<StatModel>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        PokeText(
            text = stringResource(R.string.detail_screen_stats),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        stats.forEach { stat ->
            PokemonStatItem(stat)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PokemonStatItem(
    stat: StatModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PokeText(
                text = stat.name.uppercase(),
                style = MaterialTheme.typography.labelMedium
            )

            PokeText(
                text = stat.baseStat.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = {
                (stat.baseStat / 255f).coerceIn(0f, 1f)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            strokeCap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        state = DetailUiState(
            isFavorite = false, pokemonDetail = PokemonDetailModel(
                id = 1,
                name = "bulbasaur",
                height = 7.0,
                weight = 69.0,
                abilities = listOf(
                    AbilityModel(name = "overgrow", isHidden = true),
                    AbilityModel(name = "chlorophyll", isHidden = true)
                ),
                types = listOf(PokemonType.GRASS, PokemonType.POISON),
                stats = listOf(
                    StatModel(name = "hp", baseStat = 45),
                    StatModel(name = "attack", baseStat = 49),
                    StatModel(name = "defense", baseStat = 49),
                    StatModel(name = "special-attack", baseStat = 65),
                    StatModel(name = "special-defense", baseStat = 65),
                    StatModel(name = "speed", baseStat = 45)
                ),
                imageUrl = ""
            )
        ),
        onAction = {}
    )
}
