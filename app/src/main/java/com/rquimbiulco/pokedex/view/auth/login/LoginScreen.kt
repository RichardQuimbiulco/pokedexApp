package com.rquimbiulco.pokedex.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.auth.register.RegisterUserUiState
import com.rquimbiulco.pokedex.view.core.components.PokeButtonPrimary
import com.rquimbiulco.pokedex.view.core.components.PokeButtonSecondary
import com.rquimbiulco.pokedex.view.core.components.PokeDropDownMenuItem
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.PokeTextField
import com.rquimbiulco.pokedex.view.core.components.PokeTrailingIconTextField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigatePokedexScreen: () -> Unit
) {

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val isFormLoading = uiState.authStatus is AuthStatus.Loading
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.authStatus) {
        when (uiState.authStatus) {
            is AuthStatus.Success -> navigatePokedexScreen()
            is AuthStatus.Error -> {
                snackBarHostState.showSnackbar((uiState.authStatus as AuthStatus.Error).message)
            }

            else -> {}
        }
    }

    Scaffold(
        bottomBar = {
            BottomBar(navigateToRegister = navigateToRegister)
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
                    text = uiState.userType,
                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(onClick = {
                    loginViewModel.onMenuItemSelected(
                        userType = uiState.userType,
                        true
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
                    expanded = uiState.expanded,
                    onDismissRequest = { loginViewModel.onMenuItemExpanded(false) },
                    offset = DpOffset(300.dp, 0.dp),
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    ),
                ) {
                    PokeDropDownMenuItem(
                        onClick = {
                            loginViewModel.onMenuItemSelected(
                                userType = "Admin",
                                expanded = false
                            )
                        },
                        text = stringResource(R.string.login_screen_dropdown_menu_item_admin),
                    )
                    PokeDropDownMenuItem(
                        onClick = {
                            loginViewModel.onMenuItemSelected(
                                userType = "Normal",
                                expanded = false
                            )
                        },
                        text = stringResource(R.string.login_screen_dropdown_menu_item_normal),
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
                value = uiState.email,
                onValueChange = { loginViewModel.onEmailChange(it) },
                label = stringResource(R.string.login_screen_textfield_user)
            )
            Spacer(Modifier.height(12.dp))
            PokeTrailingIconTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChange(it) },
                label = stringResource(R.string.login_screen_textfield_password),
                trailingIconId = if (uiState.passwordVisibility) {
                    R.drawable.ic_eye_password
                } else {
                    R.drawable.ic_eye_password_show
                },
                onIconClick = { loginViewModel.onPasswordShowChange(!uiState.passwordVisibility) },
                visualTransformation = if (uiState.passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
            Spacer(Modifier.height(10.dp))
            PokeButtonPrimary(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                onClick = { loginViewModel.onClickSelected() },
                enabled = uiState.isLoginEnabled,
                text = stringResource(R.string.login_screen_button_login)
            )
            TextButton(onClick = {}) {
                PokeText(text = stringResource(R.string.login_screen_button_forgot_password))
            }
        }

        if (isFormLoading) {
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
