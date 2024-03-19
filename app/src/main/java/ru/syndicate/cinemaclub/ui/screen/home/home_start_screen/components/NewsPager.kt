package ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import ru.syndicate.cinemaclub.data.model.NewsItem
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsPager(
    modifier: Modifier,
    list: List<NewsItem>,
    pagerState: PagerState
) {

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {

        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(
                horizontal = 35.dp
            ),
            beyondBoundsPageCount = 1,
            verticalAlignment = Alignment.CenterVertically
        ) { page ->

            NewsPage(
                modifier = Modifier
                    .graphicsLayer {

                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                item = list[page % list.size]
            )
        }
    }
}