package com.rquimbiulco.pokedex.view.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.auth.register.RegisterUiState
import com.rquimbiulco.pokedex.view.auth.register.RegisterUserAction
import com.rquimbiulco.pokedex.view.core.components.BackButton
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    state: DetailUiState,
    onAction: (DetailAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = {
                    PokeText(
                        text = stringResource(R.string.detail_screen_title),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(24.dp)
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
                }
            )
        },
    ) {
        Text("Detail")
    }
}