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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
fun TextRightImage(
    cardModifier: Modifier = Modifier, label: String = "", placeholder: String = "",
    onCardClick: () -> Unit = {}, imageResource: Int? = null
) {
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

                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontFamily = OpensansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )

                imageResource?.let { ImageVector.vectorResource(id = it) }?.let {
                    Image(
                        imageVector = it,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickableWithoutRipple {
                                onCardClick
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
    }
}

@Preview
@Composable
fun PreviewTextRightImage() {
    TrialJetpackComposeTheme {
        TextRightImage(
            label = stringResource(id = R.string.title_purpose_transfer),
            placeholder = stringResource(id = R.string.title_default_purpose_transfer),
            imageResource = R.drawable.ic_drop_down_maroon
        )
    }
}