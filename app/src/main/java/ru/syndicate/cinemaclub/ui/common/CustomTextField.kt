package ru.syndicate.cinemaclub.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.syndicate.cinemaclub.R
import ru.syndicate.cinemaclub.ui.theme.CustomBlue
import ru.syndicate.cinemaclub.ui.theme.CustomGray
import ru.syndicate.cinemaclub.ui.theme.TextWhite

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isEmail: Boolean = false,
    value: String = "",
    onValueChange: (String) -> Unit = { },
    hintText: String = "E-mail"
) {

    val customTextSelectionColors = TextSelectionColors(
        handleColor = CustomBlue,
        backgroundColor = CustomBlue
    )
    var visible by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = TextWhite
        ),
        singleLine = true,
        visualTransformation = if (isPassword) {
            if (visible) VisualTransformation.None else PasswordVisualTransformation()
        } else VisualTransformation.None,
        cursorBrush = SolidColor(CustomBlue),
        keyboardOptions = KeyboardOptions(
            keyboardType = when {
                isEmail -> KeyboardType.Email
                else -> KeyboardType.Password
            }
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
                            end = if (isPassword) 6.dp else 0.dp
                        )
                ) {
                    if (value.isEmpty()) {

                        Text(
                            text = hintText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = CustomGray
                        )
                    }

                    innerTextField()
                }
            }

            if (isPassword)
                IconButton(
                    modifier = Modifier
                        .size(25.dp),
                    onClick = { visible = !visible }
                ) {

                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (visible) R.drawable.svg_show_pwd
                            else R.drawable.svg_hide_pwd
                        ),
                        contentDescription = null,
                        tint = CustomGray
                    )
                }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewCustomTextField() {
    CustomTextField(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = CustomGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 15.dp,
                vertical = 12.dp
            )
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun PreviewCustomTextFieldWithPassword() {
    CustomTextField(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = CustomGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 15.dp,
                vertical = 12.dp
            ),
        isPassword = true,
        hintText = "Пароль"
    )
}