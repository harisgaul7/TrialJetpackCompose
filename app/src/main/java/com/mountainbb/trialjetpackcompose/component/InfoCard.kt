package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard(cardModifier: Modifier = Modifier, descText: String) {
    Card(
        modifier = cardModifier
            .heightIn()
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(Color(0xFFF0F0F0)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row() {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_info),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp)
            )

            Text(
                text = descText,
                style = TextStyle(
                    fontFamily = OpensansFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF333333)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            )

            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Preview
@Composable
fun PreviewInfoCard() {
    TrialJetpackComposeTheme {
        InfoCard(
            Modifier,
            stringResource(id = R.string.title_alias_desc),
        )
    }
}