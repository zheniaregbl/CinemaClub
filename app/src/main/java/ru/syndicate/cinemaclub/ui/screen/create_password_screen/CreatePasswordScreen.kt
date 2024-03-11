package ru.syndicate.cinemaclub.ui.screen.create_password_screen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.create_password_screen.components.CircleButton
import ru.syndicate.cinemaclub.ui.screen.create_password_screen.components.Indicator
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite

class CreatePasswordScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        CreatePasswordScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            createPassword = {
                navigator.pop()
            }
        )
    }
}

@Composable
fun CreatePasswordScreenContent(
    modifier: Modifier = Modifier,
    createPassword: () -> Unit = { }
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
                createPassword()
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

                    CircleButton(
                        text = "1",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 1
                            } else {
                                secondPassword += 1
                            }
                        }
                    )

                    CircleButton(
                        text = "2",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 2
                            } else {
                                secondPassword += 2
                            }
                        }
                    )

                    CircleButton(
                        text = "3",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 3
                            } else {
                                secondPassword += 3
                            }
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButton(
                        text = "4",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 4
                            } else {
                                secondPassword += 4
                            }
                        }
                    )

                    CircleButton(
                        text = "5",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 5
                            } else {
                                secondPassword += 5
                            }
                        }
                    )

                    CircleButton(
                        text = "6",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 6
                            } else {
                                secondPassword += 6
                            }
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButton(
                        text = "7",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 7
                            } else {
                                secondPassword += 7
                            }
                        }
                    )

                    CircleButton(
                        text = "8",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 8
                            } else {
                                secondPassword += 8
                            }
                        }
                    )

                    CircleButton(
                        text = "9",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 9
                            } else {
                                secondPassword += 9
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

                    CircleButton(
                        text = "0",
                        onClick = {
                            if (step == 1) {
                                firstPassword += 1
                            } else {
                                secondPassword += 1
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(80.dp)
                            .clickable {
                                when {
                                    step == 1 && firstPassword.isNotEmpty() -> firstPassword = firstPassword.dropLast(1)
                                    step == 2 && secondPassword.isNotEmpty() -> secondPassword = secondPassword.dropLast(1)
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