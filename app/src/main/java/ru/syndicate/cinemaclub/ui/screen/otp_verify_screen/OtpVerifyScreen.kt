package ru.syndicate.cinemaclub.ui.screen.otp_verify_screen

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.ui.screen.otp_verify_screen.components.OtpView
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.ProfileScreen

data class OtpVerifyScreen(
    val title: String,
    val email: String,
    val navigateToNext: () -> Unit,
    val navigateToBack: () -> Unit
) : ProfileScreen {

    override val topBarLabel: String
        get() = title

    override val isShowBonusText: Boolean
        get() = false

    @Composable
    override fun Content() {

        OtpVerifyScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            title = title,
            email = email,
            navigateToNext = { navigateToNext() },
            navigateToBack = { navigateToBack() }
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun OtpVerifyScreenContent(
    modifier: Modifier = Modifier,
    title: String = "",
    email: String = "",
    navigateToNext: () -> Unit = { },
    navigateToBack: () -> Unit = { }
) {

    val context = LocalContext.current

    val isOtpTrue = remember {
        mutableStateOf(false)
    }

    val textList = listOf(
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        ),
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange(0)
            )
        )
    )
    val requesterList = listOf(
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester()
    )

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
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Код подтверждения отправлен на почту $email",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = CustomGray
                )

                OtpView(
                    modifier = Modifier
                        .padding(
                            vertical = 10.dp
                        )
                        .fillMaxWidth(),
                    textList = textList,
                    requesterList = requesterList,
                    isOtpTrue = isOtpTrue
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
                        .clickable {
                            if (isOtpTrue.value)
                                navigateToNext()
                            else
                                Toast
                                    .makeText(
                                        context,
                                        "Problem with OTP",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                        }
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
private fun PreviewOtpScreen() {
    OtpVerifyScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            ),
        title = "Регистрация",
        email = "s*****@std.novsu.ru"
    )
}