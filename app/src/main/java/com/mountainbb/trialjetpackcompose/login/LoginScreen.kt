package com.mountainbb.trialjetpackcompose.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginCard(){
    // Create refs
    val (
        imageClose, textWelcome, userId, mpin, cbSaveUserId, forgotUser, forgotMpin, btnLogin
    ) = createRefs()
    // Dummy Data
    val userIdData = "*****ame"
    var popupControl by remember { mutableStateOf(false) }

    Surface() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { popupControl = !popupControl},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 100.dp)
            ) {
                Text(text = "To Dialog")
            }
        }

        if (popupControl) {
            CustomAlertDialog(onDismiss = { /*TODO*/ }) {
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrialJetpackComposeTheme {
        CustomAlertDialog({}, {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }, properties = DialogProperties(
        dismissOnBackPress = false,dismissOnClickOutside = false
    )
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(10.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .background(Color.White)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth(0.9f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.clear_gray),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 10.dp)
                            .width(20.dp),
                        contentDescription = null
                    )

                    Text(
                        text = stringResource(id = R.string.title_welcome),
                        style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 30.dp),
                    color = Color(0xFF888888)
                    )

                    TextFieldWithLabel(
                        labelTextField = stringResource(id = R.string.title_user_id),
                        placeHolder = "*****ama"
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    TextFieldWithLabel(
                        labelTextField = stringResource(id = R.string.title_mpin),
                        placeHolder = "MPIN"
                    )

                }

            }
        }
    }
}