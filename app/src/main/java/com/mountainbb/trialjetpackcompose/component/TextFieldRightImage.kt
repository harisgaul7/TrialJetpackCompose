package com.mountainbb.trialjetpackcompose.component

import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.focus.focusRequester
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextFieldRightImage(
    cardModifier: Modifier = Modifier, textValue: String = "", label: String = "",
    onClearClick: () -> Unit = {}, placeholder: String = "", onFavoriteClick: () -> Unit = {},
    maxLength: Int = 90, detailInfo: String = "", imageResource: Int? = null,
    onValueChange: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf(textValue) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var isClearVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = textValue, block = {
        if(textValue.isNotEmpty()) {
            text = textValue
            focusManager.clearFocus()
            isClearVisible = false
        }
    })

    Box(modifier = cardModifier
        .fillMaxWidth()) {
        Card(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(top = 6.dp)
                .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row (
                modifier = Modifier
                    .padding(top = 15.dp)
            ) {
                Spacer(modifier = Modifier.size(20.dp))

                BasicTextField(
                    value = text,
                    onValueChange = {
                        if (text.length <= maxLength){
                            text = it
                        }
                        onValueChange(text)
                        isClearVisible = text.isNotEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                        .onFocusChanged {
                            isClearVisible = it.isFocused && text != ""
                        },
                    textStyle = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.001.sp,
                        fontSize = 16.sp,
                    ),
                    singleLine = true,
                    decorationBox = {
                        Text(
                            text = if (text.isEmpty()) placeholder else "",
                            fontFamily = OpensansFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            letterSpacing = 0.001.sp,
                            color = Color.LightGray,
                            modifier = Modifier
                                .focusRequester(focusRequester)
                        )
                        it()
                    },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                            isClearVisible = false
                        }
                    )
                )

                AnimatedVisibility(visible = isClearVisible) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear_grey),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickableWithoutRipple {
                                onClearClick()
                                text = ""
                                isClearVisible = false
                            }
                    )
                }

                imageResource?.let { ImageVector.vectorResource(id = it) }?.let {
                    Image(
                        imageVector = it,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickableWithoutRipple {
                                onFavoriteClick()
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
                .padding(start = 20.dp)
                .background(Color(0xFFFFFFFF))
        )

        Text(
            text = detailInfo,
            style = TextStyle(
                fontFamily = OpensansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Start,
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 60.dp, end = 20.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCustomInputCard() {
    TrialJetpackComposeTheme {
        TextFieldRightImage(
            Modifier,
            label = stringResource(id = R.string.title_recipient_account),
            placeholder = stringResource(id = R.string.title_placeholder_type_number),
            imageResource = R.drawable.ic_favorite_not_filled
        )
    }
}