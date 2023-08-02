package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontBold
import com.mountainbb.trialjetpackcompose.ui.theme.OpensansFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SofCard(
    cardModifier: Modifier,
    amount: String = "89,700",
    amountComma: String = "00",
    accountName: String = "",
    accountNumber: String = "",
) {
    var accountVisibility by remember {
        mutableStateOf(true)
    }
    Card(
        modifier = cardModifier,
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_rekening_ponsel_card_account),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = accountName,
                        style = TextStyle(
                            fontFamily = OpensansFontBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF333333)
                        )
                    )
                    Text(
                        text = accountNumber,
                        style = TextStyle(
                            fontFamily = OpensansFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF333333)
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(top = 5.dp)
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(id = if(accountVisibility)R.drawable.visibility_off else R.drawable.visibility_on),
                        contentDescription = null,
                        modifier = Modifier
                            .width(20.dp)
                            .clickableWithoutRipple {
                                accountVisibility = !accountVisibility
                            }
                    )

                    Text(
                        text = "IDR",
                        style = TextStyle(
                            fontFamily = OpensansFontBold,
                            fontWeight = FontWeight.ExtraLight,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF333333)
                        ),
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(start = 6.dp, top = 2.dp)
                    )

                    Text(
                        text = if(accountVisibility) amount else getDigitAmount(amount),
                        style = TextStyle(
                            fontFamily = OpensansFontBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF333333)
                        ),
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(start = 6.dp, top = 1.dp)
                    )

                    Text(
                        text = if(accountVisibility) amountComma else "",
                        style = TextStyle(
                            fontFamily = OpensansFontBold,
                            fontWeight = FontWeight.ExtraLight,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF333333)
                        ),
                        modifier = Modifier
                            .align(Alignment.Top)
                            .padding(start = 2.dp)
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_dropdown_spinner),
                contentDescription = null
            )
        }
    }
}

private fun getDigitAmount(amount: String) : String {
    var bullets = ""
    for (char in amount) {
        bullets += "•"
    }
    return bullets
}

@Preview
@Composable
fun PreviewSofCard() {
    TrialJetpackComposeTheme {
        SofCard(
            Modifier
                .fillMaxWidth(0.9f)
                .height(88.dp)
        )
    }
}