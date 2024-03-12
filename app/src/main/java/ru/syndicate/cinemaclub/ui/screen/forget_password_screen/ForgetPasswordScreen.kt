package ru.syndicate.cinemaclub.ui.screen.forget_password_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.data.model.ProcessState
import ru.syndicate.cinemaclub.ui.common.CustomTextField
import ru.syndicate.cinemaclub.ui.extansions.containsUnwantedChar
import ru.syndicate.cinemaclub.ui.screen.double_password_screen.DoublePasswordScreen
import ru.syndicate.cinemaclub.ui.screen.otp_verify_screen.OtpVerifyScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.ProfileScreen
import ru.syndicate.cinemaclub.view_model.send_code_view_model.SendCodeEvent
import ru.syndicate.cinemaclub.view_model.send_code_view_model.SendCodeViewModel

class ForgetPasswordScreen : ProfileScreen {

    override val topBarLabel: String
        get() = "Восстановление пароля"

    override val isShowBonusText: Boolean
        get() = false

    @Composable
    override fun Content() {

        val sendCodeViewModel = getViewModel<SendCodeViewModel>()

        val sendCodeState by sendCodeViewModel.sendCodeState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        ForgetPasswordScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            uiState = sendCodeState,
            onSend = { email ->
                sendCodeViewModel.onEvent(SendCodeEvent.SendCode(email))
            },
            navigateToNext = { email ->

                navigator.replace(
                    OtpVerifyScreen(
                        title = "Восстановление пароля",
                        email = email,
                        navigateToNext = {
                            navigator.replace(
                                DoublePasswordScreen(
                                    title = "Восстановление пароля",
                                    name = "",
                                    email = email
                                )
                            )
                        },
                        navigateToBack = { navigator.pop() }
                    )
                )
            },
            navigateToBack = { navigator.pop() }
        )
    }
}

@Composable
fun ForgetPasswordScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProcessState = ProcessState(),
    navigateToNext: (String) -> Unit = { },
    onSend: (String) -> Unit = { },
    navigateToBack: () -> Unit = { }
) {

    var emailText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(uiState) {
        if (uiState.success)
            navigateToNext(emailText)
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
                    horizontal = 30.dp
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
                text = "Восстановление пароля",
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

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(
                            color = CustomBlue
                        )
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(8.dp)
                        .border(
                            width = 1.dp,
                            color = CustomBlue,
                            shape = RoundedCornerShape(5.dp)
                        )
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(8.dp)
                        .border(
                            width = 1.dp,
                            color = CustomBlue,
                            shape = RoundedCornerShape(5.dp)
                        )
                )
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
                        .clickable { navigateToBack() }
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Авторизация",
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
                        .clickable { onSend(emailText) }
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Далее",
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
private fun PreviewForgetPasswordScreen() {
    ForgetPasswordScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}