package ru.syndicate.cinemaclub.data.model

import com.google.gson.annotations.SerializedName

class GenreItem(
    @SerializedName("title")
    val title: String = "криминал"
) {

    override fun toString(): String {
        return title
    }
}