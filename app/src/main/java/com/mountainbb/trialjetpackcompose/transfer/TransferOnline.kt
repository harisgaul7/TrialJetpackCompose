package com.mountainbb.trialjetpackcompose.transfer

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.SofCard
import com.mountainbb.trialjetpackcompose.component.TransferTypeCard
import com.mountainbb.trialjetpackcompose.component.TrialTopAppBar
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontBold
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple
import com.mountainbb.trialjetpackcompose.util.supportWideScreen

@Composable
fun TransferScreen() {

    var isBifastSelected by remember {
        mutableStateOf(true)
    }
    var isOnlineSelected by remember {
        mutableStateOf(false)
    }
    var isSknSelected by remember {
        mutableStateOf(false)
    }
    var isRtgsSelected by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Surface(
        modifier = Modifier
            .supportWideScreen()
    ) {
        Column (
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
        ) {
            TrialTopAppBar(
                title = stringResource(id = R.string.title_other_bank),
                leftIcon = R.drawable.baseline_keyboard_arrow_left_24,
                leftIconAction = {
                    Activity().finish()
                }
            )

            Text(
                text = stringResource(id = R.string.title_transfer_from),
                style = TextStyle(
                    fontFamily = OpensansFontBold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF80000000)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 24.dp, bottom = 24.dp)
                    .align(CenterHorizontally)
            )

            SofCard(
                cardModifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(88.dp)
                    .align(CenterHorizontally)
                    .shadow(4.dp, shape = RoundedCornerShape(15.dp))
            )

            Text(
                text = stringResource(id = R.string.title_transfer_type),
                style = TextStyle(
                    fontFamily = OpensansFontBold,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF80000000)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(top = 34.dp, bottom = 24.dp)
                    .align(CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .align(CenterHorizontally)
            ) {
                TransferTypeCard(
                    Modifier
                        .weight(1f)
                        .clickableWithoutRipple {
                            isBifastSelected = true
                            isOnlineSelected = false
                            isSknSelected = false
                            isRtgsSelected = false
                        },
                    stringResource(id = R.string.title_bifast),
                    stringResource(id = R.string.title_bifast_desc),
                    isBifastSelected
                )

                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                )

                TransferTypeCard(
                    Modifier
                        .weight(1f)
                        .clickableWithoutRipple {
                            isBifastSelected = false
                            isOnlineSelected = true
                            isSknSelected = false
                            isRtgsSelected = false
                        },
                    stringResource(id = R.string.title_online),
                    stringResource(id = R.string.title_online_desc),
                    isOnlineSelected
                )
            }

            Spacer(
                modifier = Modifier
                    .size(15.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .align(CenterHorizontally)
            ) {
                TransferTypeCard(
                    Modifier
                        .weight(1f)
                        .clickableWithoutRipple {
                            isBifastSelected = false
                            isOnlineSelected = false
                            isSknSelected = true
                            isRtgsSelected = false
                        },
                    stringResource(id = R.string.title_skn),
                    stringResource(id = R.string.title_skn_desc),
                    isSknSelected
                )

                Spacer(
                    modifier = Modifier
                        .size(10.dp)
                )

                TransferTypeCard(
                    Modifier
                        .weight(1f)
                        .clickableWithoutRipple {
                            isBifastSelected = false
                            isOnlineSelected = false
                            isSknSelected = false
                            isRtgsSelected = true
                        },
                    stringResource(id = R.string.title_rtgs),
                    stringResource(id = R.string.title_rtgs_desc),
                    isRtgsSelected
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTransferScreen() {
    TrialJetpackComposeTheme {
        TransferScreen()
    }
}