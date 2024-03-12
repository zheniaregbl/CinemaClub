package ru.syndicate.cinemaclub.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import dagger.hilt.android.AndroidEntryPoint
import ru.syndicate.cinemaclub.ui.screen.splash_screen.SplashScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CinemaClubTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var cancellationSignal: CancellationSignal? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        fun checkBiometricSupport(): Boolean {
            val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

            if (!keyGuardManager.isDeviceSecure) {
                return true
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
                return false
            }

            return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
        }

        fun getCancellationSignal(): CancellationSignal {
            cancellationSignal = CancellationSignal()
            cancellationSignal?.setOnCancelListener {
                Toast.makeText(this, "Authentication Cancelled Signal", Toast.LENGTH_SHORT).show()
            }

            return cancellationSignal as CancellationSignal
        }

        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            CinemaClubTheme {

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentWindowInsets = WindowInsets.systemBars
                ) {

                    Surface(
                        modifier = Modifier
                            .fillMaxSize(),
                        color = BackgroundColor
                    ) {

                        Navigator(
                            screen = SplashScreen(
                                checkBiometricSupport = {
                                    checkBiometricSupport()
                                },
                                getCancellationSignal = {
                                    getCancellationSignal()
                                },
                                executor = mainExecutor
                            )
                        ) { navigator ->

                            FadeTransition(
                                navigator = navigator,
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = Ease
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}