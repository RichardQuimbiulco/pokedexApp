package com.rquimbiulco.pokedex.view.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.AbilityModel

@Composable
fun PokeAbilitiesCard(
    abilities: List<AbilityModel>,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(110.dp)
                    .background(accentColor)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_stars),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    PokeText(
                        text = stringResource(
                            R.string.detail_screen_abilities
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    abilities.forEach { ability ->
                        AbilityChip(
                            name = ability.name,
                            backgroundColor = accentColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AbilityChip(
    name: String,
    backgroundColor: Color
) {
    Surface(
        color = backgroundColor.copy(alpha = 0.35f),
        shape = RoundedCornerShape(12.dp)
    ) {
        PokeText(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 8.dp
            ),
            text = name.replaceFirstChar {
                it.uppercase()
            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PkeAbilityCardPreview() {
    PokeAbilitiesCard(
        abilities = listOf(
            AbilityModel(name = "Overgrow", isHidden = true),
            AbilityModel(name = "Chlorophyll", isHidden = true)
        ),
        accentColor = Color(0xFF78C850)
    )
}