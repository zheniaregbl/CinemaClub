package ru.syndicate.cinemaclub.ui.screen.verify_enter_screen

import android.annotation.SuppressLint
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.main_screen.MainScreen
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.OnboardingScreen
import ru.syndicate.cinemaclub.ui.screen.password_screen.components.CircleButtonNumber
import ru.syndicate.cinemaclub.ui.screen.password_screen.components.Indicator
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.CustomRipple
import java.util.concurrent.Executor

data class VerifyEnterScreen(
    val checkBiometricSupport: () -> Boolean,
    val getCancellationSignal: () -> CancellationSignal,
    val executor: Executor,
    val rightPassword: String
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val authenticationCallback: BiometricPrompt.AuthenticationCallback =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    navigator.replace(MainScreen())
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context, "Authentication Error code: $errorCode", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    super.onAuthenticationHelp(helpCode, helpString)
                }
            }

        @SuppressLint("NewApi")
        @RequiresApi(Build.VERSION_CODES.Q)
        fun launchBiometric() {
            if (checkBiometricSupport()) {
                val biometricPrompt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    BiometricPrompt.Builder(context)
                        .apply {
                            setTitle("Вход в Cinema Club")
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                setConfirmationRequired(false)
                            }
                            setNegativeButton("отмена", executor) { _, _, -> }
                        }.build()
                } else {
                    TODO("VERSION.SDK_INT < P")
                }

                biometricPrompt.authenticate(getCancellationSignal(), executor, authenticationCallback)
            }
        }

        VerifyEnterScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            rightCode = rightPassword,
            startBioVerify = {
                launchBiometric()
            },
            onVerify = {
                navigator.replace(MainScreen())
            }
        )
    }
}

@Composable
fun VerifyEnterScreenContent(
    modifier: Modifier = Modifier,
    rightCode: String = "1234",
    startBioVerify: () -> Unit = { },
    onVerify: () -> Unit = { }
) {

    var codeText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(codeText) {

        Log.d("checkPassword", codeText)
        Log.d("checkPassword", rightCode)

        when {
            codeText.length == 4 && codeText == rightCode -> onVerify()
            codeText.length == 4 && codeText != rightCode -> codeText = ""
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
                text = "Введите пароль",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = TextWhite
            )

            Indicator(
                text = codeText
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
                            codeText += "1"
                        }
                    )

                    CircleButtonNumber(
                        text = "2",
                        onClick = {
                            codeText += "2"
                        }
                    )

                    CircleButtonNumber(
                        text = "3",
                        onClick = {
                            codeText += "3"
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButtonNumber(
                        text = "4",
                        onClick = {
                            codeText += "4"
                        }
                    )

                    CircleButtonNumber(
                        text = "5",
                        onClick = {
                            codeText += "5"
                        }
                    )

                    CircleButtonNumber(
                        text = "6",
                        onClick = {
                            codeText += "6"
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CircleButtonNumber(
                        text = "7",
                        onClick = {
                            codeText += "7"
                        }
                    )

                    CircleButtonNumber(
                        text = "8",
                        onClick = {
                            codeText += "8"
                        }
                    )

                    CircleButtonNumber(
                        text = "9",
                        onClick = {
                            codeText += "9"
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    CompositionLocalProvider(LocalRippleTheme provides CustomRipple(CustomBlue)) {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(80.dp)
                                .clickable {
                                    startBioVerify()
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                modifier = Modifier
                                    .size(50.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.svg_biometric),
                                contentDescription = null,
                                tint = CustomGray
                            )
                        }
                    }

                    CircleButtonNumber(
                        text = "0",
                        onClick = {
                            codeText += "0"
                        }
                    )

                    CompositionLocalProvider(LocalRippleTheme provides CustomRipple(CustomBlue)) {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(80.dp)
                                .clickable {
                                    if (codeText.isNotEmpty())
                                        codeText = codeText.dropLast(1)
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
fun PreviewVerifyEnterScreen() {
    VerifyEnterScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}