package ru.syndicate.cinemaclub.ui.screen.home.home_main_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.HintTextGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite

@Composable
fun SearchLine(
    modifier: Modifier = Modifier,
    value: String = "",
    hintText: String = "Поиск",
    onValueChange: (String) -> Unit = { },
    onSearchClick: () -> Unit = { }
) {

    val customTextSelectionColors = TextSelectionColors(
        handleColor = CustomBlue,
        backgroundColor = CustomBlue
    )

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = TextWhite
        ),
        singleLine = true,
        cursorBrush = SolidColor(CustomBlue),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    ) { innerTextField ->

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            CompositionLocalProvider(
                LocalTextSelectionColors provides customTextSelectionColors
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            end = 6.dp
                        )
                ) {
                    if (value.isEmpty()) {

                        Text(
                            text = hintText,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Normal,
                            fontSize = 17.sp,
                            color = HintTextGray
                        )
                    }

                    innerTextField()
                }
            }

            IconButton(
                modifier = Modifier
                    .size(30.dp),
                onClick = { onSearchClick() }
            ) {

                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.svg_search
                    ),
                    contentDescription = null,
                    tint = CustomGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchLine() {
    SearchLine(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .padding(
                vertical = 4.dp
            )
            .padding(
                start = 8.dp,
                end = 14.dp
            )
    )
}