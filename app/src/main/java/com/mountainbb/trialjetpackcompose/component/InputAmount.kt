package com.mountainbb.trialjetpackcompose.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.ThousandSeparatorTransformation
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple
import com.mountainbb.trialjetpackcompose.util.currencyFormatter
import com.mountainbb.trialjetpackcompose.util.digitsOnly

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputAmount(
    cardModifier: Modifier = Modifier, currency: String = "", label: String = "",
    placeholder: String = "", onCurrencyClick: () -> Unit = {}, maxLength: Int = 15
) {
    var text by rememberSaveable { mutableStateOf("") }

    var isClearVisible by remember {
        mutableStateOf(false)
    }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = cardModifier
        .fillMaxWidth()
        .height(76.dp)
    ) {
        Card(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row (
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 15.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .size(20.dp)
                        .clickableWithoutRipple {
                            onCurrencyClick()
                        }
                )

                Text(
                    text = currency,
                    style = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    ),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .clickableWithoutRipple {
                            onCurrencyClick()
                        }
                )

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop_down_maroon),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)
                        .clickableWithoutRipple {
                            onCurrencyClick()
                        }
                )

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_line_vertical_grey),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp)
                        .clickableWithoutRipple {
                            onCurrencyClick()
                        }
                )

                Spacer(modifier = Modifier.size(15.dp))

                val pattern = remember { Regex("^\\d*\\.?\\d*\$") }
                BasicTextField(
                    value = text,
                    onValueChange = {
                        if (text.length <= maxLength && it.matches(pattern)){
                            text = it
                        }
                        isClearVisible = text.isNotEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp, top = 3.dp)
                        .onFocusChanged {
                            isClearVisible = it.isFocused && text != ""
                        },
                    textStyle = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.001.sp,
                        fontSize = 16.sp,
                    ),
                    decorationBox = {
                        Text(
                            text = if (text.isEmpty()) placeholder else "",
                            fontFamily = OpensansFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.001.sp,
                            color = Color.LightGray
                        )
                        it()
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = ThousandSeparatorTransformation(),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            isClearVisible = false
                        }
                    )
                )

                Spacer(modifier = Modifier.size(4.dp))

                AnimatedVisibility(visible = isClearVisible) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear_grey),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickableWithoutRipple {
                                text = ""
                                isClearVisible = false
                            }
                    )
                }

                Spacer(modifier = Modifier.size(14.dp))
            }
        }


        Text(
            text = label,
            style = TextStyle(
                fontFamily = OpensansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Start,
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 3.dp)
                .align(Alignment.TopStart)
                .background(Color(0xFFFFFFFF))
        )
    }
}

@Preview
@Composable
fun PreviewInputAmount() {
    TrialJetpackComposeTheme {
        InputAmount(
            Modifier,
            label = stringResource(id = R.string.title_transfer_amount),
            placeholder = stringResource(id = R.string.title_input_amount),
            currency = stringResource(id = R.string.title_idr)
        )
    }
}

private fun amountFormatter(
    stringText: String,
) : String {
    val removeChar = stringText.digitsOnly()
    return if (removeChar.length >= 12) {
        val amountLength = removeChar.substring(0, 12)
        setToFormat(amountLength)
    } else {
        setToFormat(removeChar)
    }
}

private fun setToFormat(
    stringText: String
) : String {
    val cleanString: String = stringText.digitsOnly()
        .replace(".", "")
    val current = when {
        cleanString.isNotEmpty() -> cleanString.toDouble().currencyFormatter()
        else -> ""
    }
    return current
}