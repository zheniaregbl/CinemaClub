package ru.syndicate.cinemaclub.ui.screen.profile_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.auth_screen.AuthScreen
import ru.syndicate.cinemaclub.ui.screen.profile_screen.components.CustomSwitch
import ru.syndicate.cinemaclub.ui.screen.profile_screen.components.SettingParam
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.LightGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite
import ru.syndicate.cinemaclub.ui.utils.CustomScreen

class ProfileScreen : CustomScreen {

    override val navBarIndex: Int
        get() = 3

    override val topBarLabel: String
        get() = "Профиль"

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        ProfileScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp
                ),
            onAuthClick = { navigator.push(AuthScreen()) }
        )
    }
}

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    onAuthClick: () -> Unit = { }
) {

    // TODO: For test
    var isAuth by remember {
        mutableStateOf(false)
    }

    var isUseLocation by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = modifier
    ) {

        item {

            Row(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .clickable {

                    }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        color = BlockBlack
                    )
                    .padding(
                        vertical = 13.dp
                    )
                    .padding(
                        start = 16.dp,
                        end = 6.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(51.dp)
                        .clickable {
                            isAuth = !isAuth
                        }
                        .background(
                            color = LightGray
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.svg_profile),
                        contentDescription = null,
                        tint = CustomGray
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    if (isAuth) {

                        Text(
                            text = "Авторизоваться",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                    } else {

                        Text(
                            text = "Иванов Иван",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextWhite
                        )

                        Text(
                            text = "sample@mail.ru",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = CustomGray
                        )
                    }

                }

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.svg_arrow),
                    contentDescription = null,
                    tint = CustomGray
                )
            }
        }

        item {

            if (!isAuth) {

                Column(
                    modifier = Modifier
                        .padding(
                            top = 32.dp
                        )
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
                            imageVector = ImageVector.vectorResource(id = R.drawable.svg_map),
                            contentDescription = null,
                            tint = CustomGray
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            text = "Геопозиция",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = CustomGray
                        )

                        CustomSwitch(
                            switchPadding = 1.dp,
                            buttonWidth = 31.dp,
                            buttonHeight = 19.dp,
                            value = isUseLocation,
                            onSwitch = {
                                isUseLocation = !isUseLocation
                            }
                        )
                    }

                    SettingParam(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        icon = R.drawable.svg_notifications,
                        textParam = "Уведомления",
                        onClick = { }
                    )

                    SettingParam(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        icon = R.drawable.svg_cards,
                        textParam = "Карты",
                        onClick = { }
                    )

                    SettingParam(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        icon = R.drawable.svg_shield,
                        textParam = "Безопасность",
                        onClick = { }
                    )
                }
            }
        }

        item {

            Column(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(
                        color = BlockBlack
                    )
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                SettingParam(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    icon = R.drawable.svg_stars,
                    textParam = "Достижения",
                    onClick = { }
                )

                SettingParam(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    icon = R.drawable.svg_diversity,
                    textParam = "Реферальная система",
                    onClick = { }
                )
            }
        }

        item {

            Box(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(
                        color = BlockBlack
                    )
                    .padding(10.dp)
            ) {

                SettingParam(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    isLogout = true,
                    icon = R.drawable.svg_logout,
                    textParam = "Выход",
                    onClick = { }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
            .padding(
                horizontal = 16.dp
            )
    )
}