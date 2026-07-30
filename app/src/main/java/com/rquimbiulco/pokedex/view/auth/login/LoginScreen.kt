package com.rquimbiulco.pokedex.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.view.core.components.PokeButtonPrimary
import com.rquimbiulco.pokedex.view.core.components.PokeButtonSecondary
import com.rquimbiulco.pokedex.view.core.components.PokeDropDownMenuItem
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.PokeTextField
import com.rquimbiulco.pokedex.view.core.components.PokeTrailingIconTextField

@Composable
fun LoginScreen(
    state: LoginUiState,
    onAction: (LoginAction) -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    Scaffold(
        bottomBar = {
            BottomBar(navigateToRegister = { onAction(LoginAction.OnRegisterClicked) })
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { padding ->
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PokeText(
                    text = state.userType.displayName(),
                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(onClick = {
                    onAction(
                        LoginAction.OnMenuItemSelected(
                            state.userType,
                            true
                        )
                    )
                }) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_person),
                            contentDescription = stringResource(R.string.login_screen_content_description_person_icon),
                        )
                        Icon(
                            modifier = Modifier.padding(top = 4.dp),
                            painter = painterResource(R.drawable.ic_arrow_drop_down),
                            contentDescription = stringResource(R.string.login_screen_content_description_arrow_icon),
                        )
                    }
                }
                DropdownMenu(
                    expanded = state.expanded,
                    onDismissRequest = { onAction(LoginAction.OnMenuItemExpanded(false)) },
                    offset = DpOffset(300.dp, 0.dp),
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    ),
                ) {
                    PokeDropDownMenuItem(
                        onClick = {
                            onAction(LoginAction.OnMenuItemSelected(UserMode.ADMIN, false))
                        },
                        text = stringResource(R.string.admin),
                    )
                    PokeDropDownMenuItem(
                        onClick = {
                            onAction(LoginAction.OnMenuItemSelected(UserMode.TRAINER, false))
                        },
                        text = stringResource(R.string.trainer),
                    )
                }
            }
            Image(
                modifier = Modifier
                    .padding(top = 22.dp)
                    .size(250.dp),
                painter = painterResource(R.drawable.ic_pokemon),
                contentDescription = stringResource(R.string.login_screen_content_description_pokemon_image)
            )
            Spacer(Modifier.weight(1.3f))
            PokeTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { onAction(LoginAction.OnEmailChanged(it)) },
                label = stringResource(R.string.login_screen_textfield_user)
            )
            Spacer(Modifier.height(12.dp))
            PokeTrailingIconTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.password,
                onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
                label = stringResource(R.string.login_screen_textfield_password),
                trailingIconId = if (state.passwordVisibility) {
                    R.drawable.ic_eye_password
                } else {
                    R.drawable.ic_eye_password_show
                },
                onIconClick = { onAction(LoginAction.OnPasswordShowChange(!state.passwordVisibility)) },
                visualTransformation = if (state.passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
            Spacer(Modifier.height(10.dp))
            PokeButtonPrimary(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                onClick = { onAction(LoginAction.OnLoginClicked) },
                enabled = state.isLoginEnabled,
                text = stringResource(R.string.login_screen_button_login)
            )
            TextButton(onClick = {}) {
                PokeText(text = stringResource(R.string.login_screen_button_forgot_password))
            }
        }

        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun BottomBar(navigateToRegister: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PokeButtonSecondary(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navigateToRegister() },
            text = stringResource(R.string.login_screen_header_new_account)
        )
        Spacer(Modifier.height(10.dp))
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = stringResource(R.string.login_screen_content_description_pokeball_image)
        )
    }
}

@Composable
fun UserMode.displayName(): String =
    when (this) {
        UserMode.TRAINER ->
            stringResource(R.string.trainer)

        UserMode.ADMIN ->
            stringResource(R.string.admin)
    }
