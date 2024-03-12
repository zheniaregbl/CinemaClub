package ru.syndicate.cinemaclub.ui.screen.auth_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.ui.common.CustomTextField
import ru.syndicate.cinemaclub.ui.extansions.containsUnwantedChar
import ru.syndicate.cinemaclub.ui.screen.forget_password_screen.ForgetPasswordScreen
import ru.syndicate.cinemaclub.ui.screen.profile_info_screen.ProfileInfoScreen
import ru.syndicate.cinemaclub.ui.screen.register_screen.RegisterScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.ProfileScreen
import ru.syndicate.cinemaclub.view_model.auth_view_model.AuthEvent
import ru.syndicate.cinemaclub.view_model.auth_view_model.AuthViewModel
import ru.syndicate.cinemaclub.view_model.profile_view_model.ProfileEvent
import ru.syndicate.cinemaclub.view_model.profile_view_model.ProfileViewModel

class AuthScreen : ProfileScreen {

    override val topBarLabel: String
        get() = "Авторизация"

    override val isShowBonusText: Boolean
        get() = false

    @Composable
    override fun Content() {

        val authViewModel = getViewModel<AuthViewModel>()

        val authState by authViewModel.authState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        AuthScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            uiState = authState,
            onAuth = { email, password ->
                authViewModel.onEvent(
                    AuthEvent.AuthUser(
                        email = email,
                        password = password
                    )
                )
            },
            navigateToNext = {
                navigator.pop()
                authViewModel.onEvent(AuthEvent.ResetState)
            },
            navigateToRegister = { navigator.push(RegisterScreen()) },
            onClickForgetPassword = { navigator.push(ForgetPasswordScreen()) }
        )
    }
}

@Composable
fun AuthScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProcessState = ProcessState(),
    onAuth: (String, String) -> Unit = { _: String, _: String -> },
    navigateToNext: () -> Unit = { },
    navigateToRegister: () -> Unit = { },
    onClickForgetPassword: () -> Unit = { }
) {

    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }

    var rememberUser by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(uiState) {
        if (uiState.success) {
            navigateToNext()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
                .clip(RoundedCornerShape(15.dp))
                .background(
                    color = BlockBlack
                )
                .padding(
                    horizontal = 20.dp
                )
                .padding(
                    top = 32.dp,
                    bottom = 35.dp
                ),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Авторизация",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = CustomBlue
            )

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(
                        width = 1.5.dp,
                        color = CustomGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(
                        horizontal = 15.dp,
                        vertical = 12.dp
                    ),
                isPassword = false,
                isEmail = true,
                value = emailText,
                onValueChange = {
                    if (!it.containsUnwantedChar()) {
                        emailText = it
                    }
                },
                hintText = "E-mail"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            width = 1.5.dp,
                            color = CustomGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(
                            horizontal = 15.dp,
                            vertical = 12.dp
                        ),
                    isPassword = true,
                    value = passwordText,
                    onValueChange = {
                        if (!it.containsUnwantedChar()) {
                            passwordText = it
                        }
                    },
                    hintText = "Пароль"
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        modifier = Modifier
                            .padding(
                                vertical = 3.dp
                            ),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    rememberUser = !rememberUser
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            if (rememberUser) {

                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.svg_checkbox_true),
                                    contentDescription = null,
                                    tint = CustomBlue
                                )
                            } else {

                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .border(
                                            width = 2.dp,
                                            color = CustomGray,
                                            shape = RoundedCornerShape(2.dp)
                                        )
                                )
                            }
                        }

                        Text(
                            text = "Запомнить меня",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = CustomGray
                        )
                    }

                    Text(
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { onClickForgetPassword() },
                        text = "Забыли пароль?",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = CustomBlue
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = CustomBlue,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { navigateToRegister() }
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Регистрация",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = CustomBlue
                )

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .background(
                            color = CustomBlue
                        )
                        .clickable {
                            onAuth(emailText, passwordText)
                        }
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Вход",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = TextWhite
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAuthScreen() {
    AuthScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}