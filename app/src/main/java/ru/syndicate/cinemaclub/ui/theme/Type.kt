package ru.syndicate.cinemaclub.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.syndicate.cinemaclub.R

val robotoSlab = FontFamily(
    Font(R.font.roboto_slab_light, FontWeight.Light),
    Font(R.font.roboto_slab_regular, FontWeight.Normal),
    Font(R.font.roboto_slab_medium, FontWeight.Medium),
    Font(R.font.roboto_slab_semibold, FontWeight.SemiBold),
    Font(R.font.roboto_slab_bold, FontWeight.Bold)
)

val roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val ptSans = FontFamily(
    Font(R.font.ptsans_regular, FontWeight.Normal),
    Font(R.font.ptsans_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = robotoSlab
    ),
    labelMedium = TextStyle(
        fontFamily = roboto
    ),
    titleMedium = TextStyle(
        fontFamily = ptSans
    )
)