package ru.syndicate.cinemaclub.ui.screen.ticket.ticket_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.screen.profile.profile_main_screen.ProfileMainScreen
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.BarColor
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.LightGray
import ru.syndicate.cinemaclub.view_model.ticket_view_model.TicketEvent
import ru.syndicate.cinemaclub.view_model.ticket_view_model.TicketViewModel

class TicketScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val ticketViewModel = getViewModel<TicketViewModel>()

        val isAuth by ticketViewModel.isAuth.collectAsState()

        LaunchedEffect(Unit) {
            ticketViewModel.onEvent(TicketEvent.CheckAuth)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = BarColor
                    )
                    .padding(
                        horizontal = 30.dp,
                        vertical = 10.dp
                    )
                    .statusBarsPadding()
            ) {

                Text(
                    text = "Мои билеты",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            if (!isAuth) {
                TicketScreenNoAuthContent(
                    modifier = Modifier
                        .fillMaxSize(),
                    navigateToAuth = {
                        navigator.replace(ProfileMainScreen())
                    }
                )
            } else {
                TicketScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun TicketScreenNoAuthContent(
    modifier: Modifier = Modifier,
    navigateToAuth: () -> Unit = { }
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.svg_secure),
                contentDescription = null
            )

            Text(
                text = "Необходима авторизация!",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = LightGray
            )

            Text(
                text = "Для просмотра приобретенных билетов,\nнеобходимо авторизоваться",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = CustomGray
            )

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { navigateToAuth() }
                    .border(
                        width = 1.dp,
                        color = CustomBlue,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp),
                text = "Авторизация",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = CustomBlue
            )
        }
    }
}

@Composable
fun TicketScreenContent(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.svg_clown),
                contentDescription = null
            )

            Text(
                text = "У вас ещё нет билетов",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = LightGray
            )

            Text(
                text = "Вы ещё не приобретали билеты",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = CustomGray
            )
        }
    }
}

@Preview
@Composable
fun PreviewTicketScreenNoAuth() {
    TicketScreenNoAuthContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = BackgroundColor
            )
    )
}