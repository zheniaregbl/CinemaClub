package ru.syndicate.cinemaclub.ui.screen.profile.password_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.OnboardingScreen
import ru.syndicate.cinemaclub.ui.screen.profile.password_screen.components.CircleButtonNumber
import ru.syndicate.cinemaclub.ui.screen.profile.password_screen.components.Indicator
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.CustomRipple
import ru.syndicate.cinemaclub.view_model.create_password_view_model.CreatePasswordEvent
import ru.syndicate.cinemaclub.view_model.create_password_view_model.CreatePasswordViewModel
import java.util.concurrent.Executor

class PasswordScreen: Screen {

    @Composable
    override fun Content() {

        val createPasswordViewModel = getViewModel<CreatePasswordViewModel>()

        val navigator = LocalNavigator.currentOrThrow

        CreatePasswordScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            createPassword = {

                createPasswordViewModel.onEvent(
                    CreatePasswordEvent.RegisterPassword(it)
                )

                navigator.pop()
            }
        )
    }
}

@Composable
fun CreatePasswordScreenContent(
    modifier: Modifier = Modifier,
    createPassword: (String) -> Unit = { }
) {

    var step by remember {
        mutableIntStateOf(1)
    }

    var firstPassword by remember {
        mutableStateOf("")
    }
    var secondPassword by remember {
        mutableStateOf("")
    }

    LaunchedEffect(firstPassword) {
        if (firstPassword.length == 4)
            step++
    }

    LaunchedEffect(secondPassword) {
        if (secondPassword.length == 4) {

            if (firstPassword == secondPassword)
                createPassword(secondPassword)
            else
                secondPassword = ""
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 50.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(60.dp)
        ) {

            Text(
                text = if (step == 1) "Создайте пароль" else "Подтвердите пароль",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = TextWhite
            )

            Indicator(
                text = if (step == 1) firstPassword else secondPassword
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButtonNumber(
                        text = "1",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "1"
                            } else {
                                secondPassword += "1"
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "2",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "2"
                            } else {
                                secondPassword += "2"
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "3",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "3"
                            } else {
                                secondPassword += "3"
                            }
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButtonNumber(
                        text = "4",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "4"
                            } else {
                                secondPassword += 4
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "5",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "5"
                            } else {
                                secondPassword += "5"
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "6",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "6"
                            } else {
                                secondPassword += "6"
                            }
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButtonNumber(
                        text = "7",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "7"
                            } else {
                                secondPassword += "7"
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "8",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "8"
                            } else {
                                secondPassword += "8"
                            }
                        }
                    )

                    CircleButtonNumber(
                        text = "9",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "9"
                            } else {
                                secondPassword += "9"
                            }
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                    )

                    CircleButtonNumber(
                        text = "0",
                        onClick = {
                            if (step == 1) {
                                firstPassword += "0"
                            } else {
                                secondPassword += "0"
                            }
                        }
                    )

                    CompositionLocalProvider(LocalRippleTheme provides CustomRipple(CustomBlue)) {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(80.dp)
                                .clickable {
                                    when {
                                        step == 1 && firstPassword.isNotEmpty() -> firstPassword =
                                            firstPassword.dropLast(1)

                                        step == 2 && secondPassword.isNotEmpty() -> secondPassword =
                                            secondPassword.dropLast(1)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.svg_delete),
                                contentDescription = null,
                                tint = CustomGray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCreatePasswordScreen() {
    CreatePasswordScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}