package ru.syndicate.cinemaclub.data.model

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Stable
data class NewsItem(
    @SerializedName("title")
    val title: String = "План на неделю",
    @SerializedName("description")
    val description: String = "12 фильмов, которые стоит посмотреть до церемонии «Оскар»",
    @SerializedName("image")
    val image: String = "https://pm1.aminoapps.com/6798/7bf71f3d8a330284feb31567f75a238712bcfef1v2_hq.jpg"
)
