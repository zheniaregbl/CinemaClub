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
    requesterList: List<FocusRequester>,
    isOtpTrue: MutableState<Boolean>
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

                    connectInputtedCode(textList) {

                        focusManager.clearFocus()
                        keyboardController?.hide()

                        if (it) {

                            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()

                            isOtpTrue.value = true

                        } else {

                            Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show()

                            textList.forEach { text ->
                                text.value = TextFieldValue(
                                    text = "",
                                    selection = TextRange(0)
                                )
                            }
                        }
                    }

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

private fun connectInputtedCode(
    textList: List<MutableState<TextFieldValue>>,
    onVerifyCode: ((Boolean) -> Unit)? = null
) {

    var code = ""
    textList.forEach {
        code += it.value.text
    }

    if (code.length == 6) {

        verifyCode(
            code = code,
            onSuccess = {
                onVerifyCode?.let {
                    it(true)
                }
            },
            onError = {
                onVerifyCode?.let {
                    it(false)
                }
            }
        )
    }
}

private fun verifyCode(
    code: String,
    onSuccess: () -> Unit,
    onError: () -> Unit
) = if (code == "123456") onSuccess() else onError()

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