package ru.syndicate.cinemaclub.ui.screen.cinema.cinema_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.data.model.CinemaItem
import ru.syndicate.cinemaclub.ui.theme.BlockBlack
import ru.syndicate.cinemaclub.ui.theme.CustomGray

@Composable
fun CinemaListItem(
    modifier: Modifier = Modifier,
    item: CinemaItem = CinemaItem(
        id = 0,
        title = "Кинотеатр",
        latitude = 0.0,
        longitude = 0.0
    )
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "7 км",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = CustomGray
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Адрес",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = CustomGray
            )
        }

        Icon(
            modifier = Modifier
                .size(40.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.svg_arrow),
            contentDescription = null,
            tint = CustomGray
        )
    }
}

@Preview
@Composable
private fun PreviewCinemaListItem() {
    CinemaListItem(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .background(
                color = BlockBlack
            )
            .padding(10.dp)
    )
}