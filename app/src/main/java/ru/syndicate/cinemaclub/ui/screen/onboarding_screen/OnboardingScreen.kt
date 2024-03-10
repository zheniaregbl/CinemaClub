package ru.syndicate.cinemaclub.ui.screen.onboarding_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.data.model.OnboardingItemData
import ru.syndicate.cinemaclub.ui.screen.main_screen.MainScreen
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.components.ImageBackground
import ru.syndicate.cinemaclub.ui.screen.onboarding_screen.components.OnboardingItem
import ru.syndicate.cinemaclub.ui.theme.CustomBlue

class OnboardingScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        OnboardingScreenContent(
            modifier = Modifier
                .fillMaxSize(),
            navigateToMainScreen = {
                navigator.replace(MainScreen())
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit = { }
) {

    val scope = rememberCoroutineScope()

    val offsetXAnimate = remember { Animatable(-1f) }

    val listOnboardingItem = listOf(
        OnboardingItemData(
            title = "Добро пожаловать\nв Клуб Синема!",
            text = "",
            image = R.drawable.svg_onboarding_image
        ),
        OnboardingItemData(
            title = "Смотри премьеры и\nклассику в кино",
            text = "Узнавай первый о премьерах новых\nфильмах и пересматривай любимые\nфильмы в кинозале",
            image = R.drawable.svg_onboarding_image2
        ),
        OnboardingItemData(
            title = "Все билеты в одном месте",
            text = "Покупай и используй билеты в\nлюбимых кинотеатрах в одном\nприложении",
            image = R.drawable.svg_onboarding_image3
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { listOnboardingItem.size }
    )

    Box(
        modifier = modifier
    ) {

        ImageBackground(
            modifier = Modifier
                .fillMaxSize(),
            state = offsetXAnimate
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.8f)
                )
                .systemBarsPadding()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = 125.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth(),
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                    userScrollEnabled = false
                ) { page ->

                    OnboardingItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        item = listOnboardingItem[page]
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(110.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = CustomBlue
                            )
                            .clickable {
                                if (pagerState.currentPage != listOnboardingItem.lastIndex) {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            page = listOnboardingItem.lastIndex,
                                            animationSpec = tween(
                                                durationMillis = 650,
                                                easing = Ease
                                            )
                                        )
                                    }
                                    scope.launch {
                                        offsetXAnimate.animateTo(
                                            targetValue = 1f,
                                            animationSpec = tween(
                                                durationMillis = 700,
                                                easing = Ease
                                            )
                                        )
                                    }
                                }
                            }
                            .padding(
                                vertical = 10.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Пропустить",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = CustomBlue
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .width(110.dp)
                            .background(
                                color = CustomBlue
                            )
                            .clickable {
                                if (pagerState.currentPage != listOnboardingItem.lastIndex) {
                                    scope.launch {
                                        pagerState.animateScrollToPage(
                                            page = pagerState.currentPage + 1,
                                            animationSpec = tween(
                                                durationMillis = 650,
                                                easing = Ease
                                            )
                                        )
                                    }
                                    scope.launch {
                                        offsetXAnimate.animateTo(
                                            targetValue = offsetXAnimate.value + 1f,
                                            animationSpec = tween(
                                                durationMillis = 700,
                                                easing = Ease
                                            )
                                        )
                                    }
                                } else {
                                    navigateToMainScreen()
                                }
                            }
                            .padding(
                                vertical = 10.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = if (pagerState.currentPage != listOnboardingItem.lastIndex) "Далее"
                            else "Начать",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    )
}