package com.mountainbb.trialjetpackcompose.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.InputTypeClass
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme

@Composable
fun LoginDialog(onDismiss: () -> Unit, onClickLogin: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        var saveUserStateButton by remember {
            mutableStateOf(true)
        }
        var imageUserStateButton by remember {
            mutableStateOf(R.drawable.checkbox_on)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(Color.White, shape = RoundedCornerShape(5.dp))
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clear_gray),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 10.dp)
                        .width(15.dp)
                        .clickable {
                            onDismiss()
                        },
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.title_welcome),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 28.dp),
                    color = Color(0xFF6B6A6A)
                )

                TextFieldWithLabel(
                    labelTextField = stringResource(id = R.string.title_user_id),
                    value = "*****ama",
                    enabled = false
                )

                Spacer(modifier = Modifier.size(5.dp))

                TextFieldWithLabel(
                    labelTextField = stringResource(id = R.string.title_mpin),
                    inputType = InputTypeClass.Password,
                    maxChar = 6
                )

                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 20.dp),
                ) {
                    if (saveUserStateButton) {
                        imageUserStateButton = R.drawable.checkbox_on
                    } else {
                        imageUserStateButton = R.drawable.checkbox_off
                    }
                    Image(
                        painter = painterResource(id = imageUserStateButton),
                        contentDescription = "Unchecked",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 12.dp)
                            .clickable {
                                saveUserStateButton = !saveUserStateButton
                            }
                    )

                    Text(
                        text = stringResource(id = R.string.title_save_user_id),
                        style = TextStyle(
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 0.15.sp,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(id = R.string.title_forgot_user_id),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Color(0xFF1273A7)
                )

                Spacer(
                    modifier = Modifier
                        .weight(1F)
                )

                Text(
                    text = stringResource(id = R.string.title_forgot_mpin),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Color(0xFF1273A7)
                )
            }

            Button(
                onClick = {
                    onClickLogin
                },
                modifier = Modifier
                    .fillMaxWidth(0.84F)
                    .padding(top = 15.dp, bottom = 3.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF33C3D6))
            ) {
                Text(
                    text = stringResource(id = R.string.title_login),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrialJetpackComposeTheme {
        LoginDialog({}, {})
    }
}
