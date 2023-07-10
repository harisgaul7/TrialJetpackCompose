package com.mountainbb.trialjetpackcompose.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.MaskTransformation
import com.mountainbb.trialjetpackcompose.util.supportWideScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen () {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(painter = painterResource(id = R.drawable.figma_seeklogo_com),
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(5.dp)
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null)

            Text(text = stringResource(id = R.string.title_sign_in),
                style = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    letterSpacing = 0.15.sp,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Text(text = stringResource(id = R.string.sign_in_create_account),
                style = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 8.sp,
                    letterSpacing = 0.15.sp,
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 20.dp)
            )

            val value = remember {
                mutableStateOf(TextFieldValue())
            }
            val valueEdit = remember {
                mutableStateOf(TextFieldValue("UserId"))
            }
            val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
//            OutlinedTextField(
//                value = value.value,
//                onValueChange = {
//                    value.value = it
//                },
//                modifier = Modifier
//                    .fillMaxWidth(0.8f)
//                    .height(50.dp)
//                    .align(Alignment.CenterHorizontally),
//                textStyle = TextStyle(
//                    fontFamily = MontserratFontFamily,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 14.sp,
//                    lineHeight = 24.sp,
//                    letterSpacing = 0.15.sp,
//                ),
//                keyboardOptions = KeyboardOptions.Default.copy(
//                    imeAction = ImeAction.Done,
//                    keyboardType = KeyboardType.Email
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }
//                )
//            )
//
//            Spacer(
//                modifier = Modifier
//                    .height(20.dp)
//            )

            TextFieldWithLabel("User ID", "*****ame")

            Button(
                onClick = {} ,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 15.dp, bottom = 3.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(id = R.string.title_continue),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TrialJetpackComposeTheme {
        LoginScreen()
    }
}