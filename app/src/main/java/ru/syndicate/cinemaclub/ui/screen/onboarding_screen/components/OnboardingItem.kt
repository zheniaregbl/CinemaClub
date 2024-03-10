package ru.syndicate.cinemaclub.ui.screen.onboarding_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.data.model.OnboardingItemData

@Composable
fun OnboardingItem(
    modifier: Modifier = Modifier,
    item: OnboardingItemData = OnboardingItemData(
        title = "Смотри премьеры и\nклассику в кино",
        text = "Узнавай первый о премьерах новых\nфильмах и пересматривай любимые\nфильмы в кинозале",
        image = R.drawable.svg_onboarding_image
    )
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(200.dp),
            imageVector = ImageVector.vectorResource(id = item.image),
            contentDescription = null
        )

        Spacer(
            modifier = Modifier
                .height(48.dp)
        )

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Spacer(
            modifier = Modifier
                .height(26.dp)
        )

        Text(
            text = item.text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewOnboardingItem() {
    OnboardingItem(
        modifier = Modifier
            .fillMaxWidth()
    )
}