package com.mountainbb.trialjetpackcompose.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel

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
                .width(300.dp)
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.clear_gray),
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        popupControl = !popupControl
                    },
                contentDescription = null
            )

            TextFieldWithLabel(
                labelTextField = stringResource(id = R.string.title_user_id),
                placeHolder = userIdData
            )

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
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clear_gray),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                    contentDescription = null
                )

                TextFieldWithLabel(
                    labelTextField = stringResource(id = R.string.title_user_id),
                    placeHolder = "userIdData"
                )
            }
        }
    }
}