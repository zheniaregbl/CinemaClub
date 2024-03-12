package ru.syndicate.cinemaclub.ui.screen.otp_verify_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OtpView(
    modifier: Modifier,
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        itemsIndexed(textList) { index, item ->
            OtpBox(
                value = item.value,
                onValueChange = { newValue ->

                    if (item.value.text != "") {

                        if (newValue.text == "") {
                            item.value = TextFieldValue(
                                text = "",
                                selection = TextRange(0)
                            )
                        }

                        return@OtpBox
                    }

                    item.value = TextFieldValue(
                        text = newValue.text,
                        selection = TextRange(newValue.text.length)
                    )

                    nextFocus(textList, requesterList)
                },
                focusRequester = requesterList[index]
            )
        }
    }

    LaunchedEffect(Unit) {
        requesterList[0].requestFocus()
    }
}

private fun nextFocus(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>
) {

    for (i in textList.indices) {

        if (textList[i].value.text == "") {
            if (i < textList.size) {

                requesterList[i].requestFocus()
                break
            }
        }
    }
}