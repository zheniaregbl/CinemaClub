package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("news_list")
    val newsList: List<NewsItem>
)