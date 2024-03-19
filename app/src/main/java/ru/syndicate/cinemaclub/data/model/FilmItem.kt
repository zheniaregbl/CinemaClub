package ru.syndicate.cinemaclub.data.model

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Stable
class FilmItem(
    @SerializedName("id")
    val id: Int = 1,
    @SerializedName("title")
    val title: String = "Зеленая миля",
    @SerializedName("poster")
    val poster: String = "https://gas-kvas.com/uploads/posts/2023-01/1673333614_gas-kvas-com-p-anime-risunki-mob-psikho-100-4.jpg",
    @SerializedName("genre")
    val genre: List<GenreItem> = listOf(GenreItem(), GenreItem(), GenreItem()),
    @SerializedName("trailers")
    val listTrailers: List<TrailerItem> = listOf(TrailerItem()),
    @SerializedName("eng_title")
    val engTitle: String? = "The Green Mile",
    @SerializedName("god")
    val year: String = "1999",
    @SerializedName("country")
    val country: String = "США",
    @SerializedName("timing")
    val timing: Int = 189,
    @SerializedName("age")
    val age: String = "18+",
    @SerializedName("review_title")
    val reviewTitle: String? = null,
    @SerializedName("review_text")
    val reviewText: String = "Пол Эджкомб — начальник блока смертников в тюрьме ..."
) {

    override fun toString(): String {
        return title
    }
}