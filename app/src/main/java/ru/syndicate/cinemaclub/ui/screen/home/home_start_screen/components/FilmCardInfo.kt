package ru.syndicate.cinemaclub.ui.screen.home.home_start_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.syndicate.cinemaclub.core.CoreConstants
import ru.syndicate.cinemaclub.data.model.FilmItem
import ru.syndicate.cinemaclub.ui.theme.BackgroundColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite

@Composable
fun FilmCardInfo(
    modifier: Modifier = Modifier,
    item: FilmItem = FilmItem()
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {

            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(13.dp))
                    .height(200.dp)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(CoreConstants.BASE_URL + item.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 8.dp,
                        bottom = 4.dp
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .background(
                        color = BackgroundColor
                    )
                    .padding(
                        vertical = 3.dp,
                        horizontal = 5.dp
                    ),
                text = item.age,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = TextWhite
            )
        }

        Text(
            text = item.title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = TextWhite
        )

        Text(
            text = item.genre.joinToString(", "),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = CustomGray
        )
    }
}

@Preview
@Composable
private fun PreviewFilmCardInfo() {
    FilmCardInfo()
}