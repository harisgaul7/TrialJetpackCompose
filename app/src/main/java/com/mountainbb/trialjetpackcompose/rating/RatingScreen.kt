package com.mountainbb.trialjetpackcompose.rating

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.ratingBar
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.util.clickableWithoutRipple

@Composable
fun RatingDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        var valueEdit = remember {
            mutableStateOf(TextFieldValue(""))
        }
        val context = LocalContext.current
        var ratingValue by remember {
            mutableStateOf(1)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .background(Color.White, RoundedCornerShape(15.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.rating_background),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(0.8f)
                    .align(CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = handleRatingImage(ratingValue)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .weight(0.4f)
                        .padding(start = 30.dp)
                        .align(Alignment.CenterVertically)
                )

                Text(
                    text = stringResource(id = R.string.title_rate_us),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.Center
                    ),
                    color = Color(0xFF179ED5),
                    modifier = Modifier
                        .weight(0.2f)
                        .align(Alignment.CenterVertically)
                )

                Image(
                    painter = painterResource(id = handleRatingImage(ratingValue)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .weight(0.4f)
                        .padding(end = 30.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Text(
                text = stringResource(id = R.string.title_support),
                style = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .align(CenterHorizontally)
            )

            ratingValue = ratingBar(
                modifier = Modifier
                    .align(CenterHorizontally)
            )

            OutlinedTextField(
                value = valueEdit.value,
                onValueChange = {
                    if (it.text.length <= 200){
                        valueEdit.value = it
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(0.8f)
                    .height(100.dp)
                    .align(CenterHorizontally),
                maxLines = 5,
                placeholder = {
                    Text(
                        text = "Write your feedback here...",
                        style = TextStyle(
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        ),
                        color = Color(0xFFBEB4B4),
                    )
                },
                textStyle = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF000000)
                ),
            )

            Button(
                onClick = {
                    Toast.makeText(context, "Ulasan anda berhasil dikirim", Toast.LENGTH_SHORT).show()
                    onDismiss()
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(CenterHorizontally)
                    .fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(Color(0xFF179ED5))
            ) {
                Text(
                    text = stringResource(id = R.string.title_send),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color.White,
                )
            }

            Text(
                text = stringResource(id = R.string.title_later),
                style = TextStyle(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                color = Color(0xFFBEB4B4),
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .align(CenterHorizontally)
                    .clickableWithoutRipple {
                        onDismiss()
                    }
            )
        }

    }
}

fun handleRatingImage(rating: Int) : Int {
    return when (rating) {
        1 -> R.drawable.emoji_mad
        2 -> R.drawable.emoji_semi_mad
        3 -> R.drawable.emoji_flat
        4 -> R.drawable.emoji_semi_happy
        else -> R.drawable.emoji_happy
    }
}

@Preview
@Composable
fun RatingBarPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        RatingDialog {}
    }
}