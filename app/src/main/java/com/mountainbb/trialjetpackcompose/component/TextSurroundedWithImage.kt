package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextSurroundedWithImage(
    cardModifier: Modifier = Modifier, label: String = "", placeholder: String = "",
    leftImage: Int = R.drawable.ic_bank, onBankClick: () -> Unit = {},
    rightImage: Int = R.drawable.ic_drop_down_maroon
) {
    Box(modifier = cardModifier
        .fillMaxWidth()
        .height(76.dp)
        .clickableWithoutRipple {
            onBankClick
        }
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
                    .padding(top = 13.dp)
            ) {
                Spacer(modifier = Modifier.size(18.dp))

                Image(
                    imageVector = ImageVector.vectorResource(id = leftImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF80000000)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                Image(
                    imageVector = ImageVector.vectorResource(id = rightImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.size(14.dp))
            }
        }


        Text(
            text = label,
            style = TextStyle(
                fontFamily = OpensansFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = 0.15.sp,
                textAlign = TextAlign.Start,
                color = Color(0xFF000000)
            ),
            modifier = Modifier
                .padding(start = 20.dp, top = 2.dp)
                .align(Alignment.TopStart)
                .background(Color(0xFFFFFFFF))
        )
    }
}

@Preview
@Composable
fun PreviewSelectBank() {
    TrialJetpackComposeTheme {
        TextSurroundedWithImage(
            Modifier,
            label = stringResource(id = R.string.title_bank_name),
            placeholder = stringResource(id = R.string.title_select_bank),
        )
    }
}