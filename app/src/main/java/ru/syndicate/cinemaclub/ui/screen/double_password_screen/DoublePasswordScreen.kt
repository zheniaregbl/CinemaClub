package ru.syndicate.cinemaclub.ui.screen.double_password_screen

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.ui.common.CustomTextField
import ru.syndicate.cinemaclub.ui.extansions.containsUnwantedChar
import ru.syndicate.cinemaclub.ui.screen.auth_screen.AuthScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.ProfileScreen

data class DoublePasswordScreen(
    val title: String
) : ProfileScreen {

    override val topBarLabel: String
        get() = title

    override val isShowBonusText: Boolean
        get() = false

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        DoublePasswordScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            title = title,
            navigateToNext = { navigator.popUntil { it is AuthScreen } },
            navigateToBack = { navigator.pop() }
        )
    }
}

@Composable
fun DoublePasswordScreenContent(
    modifier: Modifier = Modifier,
    title: String = "Регистрация",
    navigateToNext: () -> Unit = { },
    navigateToBack: () -> Unit = { }
) {

    var passwordText by remember {
        mutableStateOf("")
    }
    var repeatPasswordText by remember {
        mutableStateOf("")
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
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = CustomBlue
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 20.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
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
                    value = repeatPasswordText,
                    onValueChange = {
                        if (!it.containsUnwantedChar()) {
                            repeatPasswordText = it
                        }
                    },
                    hintText = "Повторите пароль"
                )
            }

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
                        .clip(RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(
                            color = CustomBlue
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
                    text = "Назад",
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
                        .clickable { navigateToNext() }
                        .padding(
                            vertical = 10.dp
                        ),
                    text = "Завершить",
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
private fun PreviewDoublePasswordScreen() {
    DoublePasswordScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}