package ru.syndicate.cinemaclub.ui.screen.profile.profile_main_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.CustomRed

@Composable
fun SettingParam(
    modifier: Modifier = Modifier,
    isLogout: Boolean = false,
    icon: Int = R.drawable.svg_shield,
    textParam: String = "Безопасность"
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = null,
            tint = if (isLogout) CustomRed else CustomGray
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            text = textParam,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = CustomGray
        )

        Icon(
            modifier = Modifier
                .size(25.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.svg_arrow),
            contentDescription = null,
            tint = CustomGray
        )
    }
}

@Preview
@Composable
private fun PreviewSettingParam() {
    SettingParam(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}