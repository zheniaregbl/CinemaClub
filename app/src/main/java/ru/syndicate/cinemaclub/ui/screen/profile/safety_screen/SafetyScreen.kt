package ru.syndicate.cinemaclub.ui.screen.profile.safety_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.profile.password_screen.PasswordScreen
import ru.syndicate.cinemaclub.ui.screen.profile.profile_main_screen.components.CustomSwitch
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.utils.ProfileScreen
import ru.syndicate.cinemaclub.view_model.safety_view_model.SafetyEvent
import ru.syndicate.cinemaclub.view_model.safety_view_model.SafetyViewModel

data class SafetyScreen(
    val onBack: () -> Unit
) : ProfileScreen {

    override val topBarLabel: String
        get() = "Безопасность"

    override val isShowBonusText: Boolean
        get() = false

    override val backText: String
        get() = "< Профиль"

    override val onClickBack: () -> Unit
        get() = onBack

    @Composable
    override fun Content() {

        val safetyViewModel = getViewModel<SafetyViewModel>()

        val haveAppPassword by safetyViewModel.haveAppPassword.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        SafetyScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            haveAppPassword = haveAppPassword,
            navigateToPassword = {
                navigator.parent!!.parent!!.push(PasswordScreen())
            },
            deletePassword = {
                safetyViewModel.onEvent(SafetyEvent.DeletePassword)
            },
            onLaunch = {
                safetyViewModel.onEvent(SafetyEvent.CheckHavePassword)
            }
        )
    }
}

@Composable
fun SafetyScreenContent(
    modifier: Modifier = Modifier,
    haveAppPassword: Boolean = false,
    navigateToPassword: () -> Unit = { },
    deletePassword: () -> Unit = { },
    onLaunch: () -> Unit = { }
) {

    var useBiometric by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        onLaunch()
    }

    Column(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(
                    color = BlockBlack
                )
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.svg_code),
                    contentDescription = null,
                    tint = CustomGray
                )

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    text = "Код-пароль",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = CustomGray
                )

                CustomSwitch(
                    switchPadding = 1.dp,
                    buttonWidth = 31.dp,
                    buttonHeight = 19.dp,
                    value = haveAppPassword,
                    onSwitch = {
                        if (!haveAppPassword) {
                            navigateToPassword()
                        } else {
                            deletePassword()
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.svg_biometric),
                    contentDescription = null,
                    tint = CustomGray
                )

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    text = "Биометрия",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = CustomGray
                )

                CustomSwitch(
                    switchPadding = 1.dp,
                    buttonWidth = 31.dp,
                    buttonHeight = 19.dp,
                    value = useBiometric,
                    onSwitch = {
                        useBiometric = !useBiometric
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSafetyScreen() {
    SafetyScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
            .padding(16.dp)
    )
}