package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferTypeCard(cardModifier: Modifier = Modifier, titleText: String, descText: String, isSelected: Boolean = false) {
    Card(
        modifier = cardModifier
            .height(85.dp)
            .border(
                1.dp,
                if (isSelected) Color(0xFF83C048) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = titleText,
                style = TextStyle(
                    fontFamily = OpensansFontBold,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = descText,
                style = TextStyle(
                    fontFamily = OpensansFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 13.dp, end = 13.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Preview
@Composable
fun PreviewTransferTypeCard() {
    TrialJetpackComposeTheme {
        TransferTypeCard(
            Modifier,
            stringResource(id = R.string.title_bifast),
            stringResource(id = R.string.title_bifast_desc),
            true
        )
    }
}