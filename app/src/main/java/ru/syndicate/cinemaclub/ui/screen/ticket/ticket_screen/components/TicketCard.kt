package ru.syndicate.cinemaclub.ui.screen.ticket.ticket_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.QrColors
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.theme.BarColor
import ru.syndicate.cinemaclub.ui.theme.CustomGray

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TicketCardFuture() {

    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )

    Row(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 10.dp
            )
            .height(180.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = BarColor
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 3.dp
                    )
                    .vertical()
                    .rotate(-90f),
                text = "No. RT87671997",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = CustomGray
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = BarColor
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(
                        color = CustomGray
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        vertical = 10.dp,
                        horizontal = 10.dp
                    )
                    .width(200.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {

                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.elem),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = "КЛУБ СИНЕМА",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        color = Color.White
                    )

                    Image(
                        modifier = Modifier
                            .rotate(180f),
                        imageVector = ImageVector.vectorResource(id = R.drawable.elem),
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                color = CustomGray
                            )
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Кинотеатр, Адрес",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = CustomGray
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                color = CustomGray
                            )
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 10.dp
                        )
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "ФИЛЬМ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "ЖАНР",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 10.sp,
                        color = CustomGray
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                color = CustomGray
                            )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .width(40.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "ЗАЛ",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = CustomGray
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "01",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(40.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "РЯД",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = CustomGray
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "7",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(40.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "МЕСТО",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = CustomGray
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "6",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }

                        Column(
                            modifier = Modifier
                                .width(40.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "ВРЕМЯ",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = CustomGray
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "17:20",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(
                                color = CustomGray
                            )
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "27 февраля 2024",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp,
                    color = CustomGray
                )
            }
        }

        Column(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(
                    vertical = 8.dp
                ),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            repeat(100) {

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(
                            color = Color.White
                        )
                )
            }
        }

        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .fillMaxHeight()
                .background(
                    color = BarColor
                )
                .width(88.dp)
                .padding(
                    horizontal = 8.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(60.dp),
                painter = rememberQrCodePainter(
                    data = ""
                ),
                contentDescription = null,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                maxItemsInEachRow = 2
            ) {

                Column(
                    modifier = Modifier
                        .width(36.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "ВРЕМЯ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = CustomGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "17:20",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .width(36.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "ЗАЛ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = CustomGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "01",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .width(36.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "РЯД",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = CustomGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "7",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .width(36.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "МЕСТО",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = CustomGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "6",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
private fun PreviewTicketCardFuture() {
    TicketCardFuture()
}

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.height, placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}