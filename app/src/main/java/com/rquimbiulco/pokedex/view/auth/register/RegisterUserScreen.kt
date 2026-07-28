package com.rquimbiulco.pokedex.view.auth.register

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.core.components.MyPokeRegisterRadioButton
import com.rquimbiulco.pokedex.view.core.components.PokeButtonPrimary
import com.rquimbiulco.pokedex.view.core.components.PokeText
import com.rquimbiulco.pokedex.view.core.components.PokeTextField
import com.rquimbiulco.pokedex.view.core.components.PokeTrailingIconTextField

@Composable
fun RegisterUserScreen(
    state: RegisterUiState,
    onAction: (RegisterUserAction) -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    Scaffold(
        topBar = {
            TopBar(navigateBack = { onAction(RegisterUserAction.OnBackButtonClicked) })
        },
        bottomBar = {
            BottomBar(
                isSaveEnabled = state.isSaveEnabled && !state.isLoading,
                onSaveClick = { onAction(RegisterUserAction.OnSaveButtonClicked) })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { padding ->
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxSize(),
        ) {
            AnimatedContent(stringResource(R.string.register_screen_title_email)) { targetTitle ->
                PokeText(
                    text = targetTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(12.dp))
            PokeText(
                text = stringResource(R.string.register_screen_subtitle_email),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12.dp))
            PokeTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.registerForm.email,
                onValueChange = { onAction(RegisterUserAction.OnEmailChanged(it)) },
                label = stringResource(R.string.register_screen_textfield_email),
                shape = MaterialTheme.shapes.medium
            )
            Spacer(Modifier.height(10.dp))
            AnimatedVisibility(visible = state.showEmailFormatError) {
                PokeText(
                    text = stringResource(R.string.register_screen_text_wrong_email_format),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(Modifier.height(10.dp))
            PokeTrailingIconTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.registerForm.password,
                onValueChange = { onAction(RegisterUserAction.OnPasswordChanged(it)) },
                label = stringResource(R.string.register_screen_textfield_password),
                trailingIconId = if (state.passwordVisibility) {
                    R.drawable.ic_eye_password
                } else {
                    R.drawable.ic_eye_password_show
                },
                onIconClick = { onAction(RegisterUserAction.OnPasswordShowChange(!state.passwordVisibility)) }, //registerViewModel.onPasswordShowChange(!uiState.passwordVisibility)
                visualTransformation = if (state.passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
            Spacer(Modifier.height(10.dp))
            AnimatedVisibility(visible = state.isValidPassword) {
                PokeText(
                    text = stringResource(R.string.register_screen_text_is_not_valid_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(Modifier.height(10.dp))
            PokeTrailingIconTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.registerForm.confirmPassword,
                onValueChange = { onAction(RegisterUserAction.OnConfirmPasswordChanged(it)) },
                label = stringResource(R.string.register_screen_textfield_confirm_password),
                trailingIconId = if (state.confirmPasswordVisibility) {
                    R.drawable.ic_eye_password
                } else {
                    R.drawable.ic_eye_password_show
                },
                onIconClick = { onAction(RegisterUserAction.OnConfirmPasswordShowChange(!state.confirmPasswordVisibility)) },
                visualTransformation = if (state.confirmPasswordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
            Spacer(Modifier.height(10.dp))
            AnimatedVisibility(visible = state.showPasswordMatchError) {
                PokeText(
                    text = stringResource(R.string.register_screen_text_wrong_confirm_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(Modifier.height(50.dp))
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
            ) {
                val (regularRadioButton, adminRadioButton) = createRefs()
                createHorizontalChain(
                    regularRadioButton,
                    adminRadioButton,
                    chainStyle = ChainStyle.Spread
                )
                MyPokeRegisterRadioButton(
                    modifier = Modifier.constrainAs(regularRadioButton) {
                        top.linkTo(parent.top)
                    },
                    icon = painterResource(R.drawable.ic_pokeball),
                    contentDescription = stringResource(R.string.register_screen_content_description_trainer_regular_icon),
                    text = stringResource(R.string.register_screen_radio_button_trainer),
                    onClick = { onAction(RegisterUserAction.OnUserTypeChanged(it)) }, //registerViewModel.onUserTypeChange(it)
                    selected = state.registerForm.userType.userType == 0
                )
                MyPokeRegisterRadioButton(
                    modifier = Modifier
                        .constrainAs(adminRadioButton) {
                            top.linkTo(parent.top)
                        },
                    icon = painterResource(R.drawable.pokemon_trainer),
                    contentDescription = stringResource(R.string.register_screen_content_description_trainer_icon),
                    text = stringResource(R.string.register_screen_radio_button_admin),
                    onClick = { onAction(RegisterUserAction.OnUserTypeChanged(it)) },
                    selected = state.registerForm.userType.userType == 1
                )
            }
        }
    }
    if (state.isLoading) {
        CircularProgressBar()
    }
}

@Composable
fun CircularProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 4.dp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigateBack: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = stringResource(R.string.register_screen_content_description_back_icon),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { navigateBack() }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}

@Composable
fun BottomBar(isSaveEnabled: Boolean, onSaveClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre botón e imagen
    ) {
        PokeButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            onClick = { onSaveClick() },
            enabled = isSaveEnabled,
            text = stringResource(R.string.register_screen_button_save_user)
        )
        Spacer(Modifier.height(10.dp))
        Image(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription = stringResource(R.string.login_screen_content_description_pokeball_image)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterUserScreenPreview() {
    RegisterUserScreen(
        state = RegisterUiState(),
        onAction = {},
        snackBarHostState = remember { SnackbarHostState() }
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterUserScreenLoadingPreview() {
    RegisterUserScreen(
        state = RegisterUiState(
            isLoading = true
        ),
        onAction = {},
        snackBarHostState = remember { SnackbarHostState() }
    )
}

@Preview(showBackground = true)
@Composable
private fun RegisterUserScreenFilledPreview() {
    RegisterUserScreen(
        state = RegisterUiState(
            registerForm = RegisterForm(
                email = "ash@pokemon.com",
                password = "123456",
                confirmPassword = "123456"
            ),
            isSaveEnabled = true
        ),
        onAction = {},
        snackBarHostState = remember { SnackbarHostState() }
    )
}