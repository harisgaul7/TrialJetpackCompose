package com.mountainbb.trialjetpackcompose.welcome

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.DragDialog
import com.mountainbb.trialjetpackcompose.component.datePickerDialog
import com.mountainbb.trialjetpackcompose.login.LoginDialog
import com.mountainbb.trialjetpackcompose.rating.RatingDialog
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.SeptimusFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.supportWideScreen
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onContinue: () -> Unit
) {
    var cardInfoVisibility by remember {
        mutableStateOf(1)
    }
    var marginTextTitle by remember {
        mutableStateOf(105)
    }
    var cardWarningVisibility by remember {
        mutableStateOf(true)
    }
    var dragBarStatus by remember {
        mutableStateOf(false)
    }
    var loginStatus by remember {
        mutableStateOf(false)
    }
    var datePickerStatus by remember {
        mutableStateOf(false)
    }
    var ratingBarStatus by remember {
        mutableStateOf(false)
    }

    // to disable ripple effect when button info is clicked
    val interactionSource = remember {
        MutableInteractionSource()
    }
    // to make drag and drop chat us
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var marginModifierWarning by remember {
        mutableStateOf(170)
    }

    Surface(
        modifier = Modifier
            .supportWideScreen()
    ) {
        Column(
            modifier = Modifier
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds,
                    sizeToIntrinsics = false
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.info
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            cardInfoVisibility++
                        }
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                Text(
                    text = stringResource(id = R.string.title_other_menu),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .width(40.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_format_align_right_24),
                    contentDescription = null,
                )
            }

            if (cardInfoVisibility % 2 == 0) {
                marginTextTitle = 35
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .padding(top = 7.dp, start = 32.dp)
                        .align(Alignment.Start),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEEF7F7)
                    )
                ) {
                    Text(
                        text = "PT. Bank Negara Indonesia (Persero) TBK. terdaftar dan diawasi oleh " +
                                "Otoritas Jasa Keuangan (OJK), serta dijamin       Lembaga Penjamin Simpanan (LPS).",
                        modifier = Modifier
                            .padding(end = 5.dp, top = 5.dp, bottom = 5.dp),
                        style = TextStyle(
                            color = Color(0xFF0A5674),
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            letterSpacing = 0.15.sp,
                            textAlign = TextAlign.End
                        ),
                        lineHeight = 15.sp
                    )
                }
            } else {
                marginTextTitle = 105
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = marginTextTitle.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bni46),
                    modifier = Modifier
                        .width(40.dp)
                        .padding(top = 4.dp),
                    contentDescription = null,
                )

                Spacer(
                    modifier = Modifier
                        .padding(2.dp)
                )

                Text(
                    text = stringResource(id = R.string.title_bni),
                    style = TextStyle(
                        color = Color(0xFF0A5674),
                        fontFamily = SeptimusFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 74.sp,
                        letterSpacing = 1.sp,
                    ),
                    modifier = Modifier
                        .clickable {
                            dragBarStatus = !dragBarStatus
                        }
                )
            }

            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )

            Button(
                onClick = {
                    loginStatus = !loginStatus
                },
                modifier = Modifier
                    .fillMaxWidth(0.84F)
                    .padding(top = 15.dp, bottom = 3.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF33C3D6))
            ) {
                Text(
                    text = stringResource(id = R.string.title_login),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color.White,
                )

                var resourceImages by remember { mutableStateOf(R.drawable.fingerprint) }

                val isTimerActive by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(key1 = isTimerActive) {
                    if (isTimerActive) {
                        var second = 0
                        while (second >= 0) {
                            resourceImages = if (second % 2 == 1) {
                                R.drawable.face_recognation
                            } else {
                                R.drawable.fingerprint
                            }
                            delay(1500)
                            second++
                        }
                    }
                }

                Image(
                    painter = painterResource(id = resourceImages),
                    modifier = Modifier
                        .width(33.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }

            Button(
                onClick = { datePickerStatus = !datePickerStatus },
                modifier = Modifier
                    .fillMaxWidth(0.84F)
                    .padding(top = 20.dp, bottom = 3.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF33C3D6)),
                colors = ButtonDefaults.buttonColors(Color(0xFFFCFCFC))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.wallet),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .width(15.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = stringResource(id = R.string.title_top_up),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color(0xFF33C3D6),
                )
            }

            Button(
                onClick = { ratingBarStatus = !ratingBarStatus },
                modifier = Modifier
                    .fillMaxWidth(0.84F)
                    .padding(top = 1.dp, bottom = 3.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color(0xFF33C3D6)),
                colors = ButtonDefaults.buttonColors(Color(0xFFFCFCFC))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.qris),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .width(32.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = stringResource(id = R.string.title_qris),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color(0xFF33C3D6),
                )
            }

            if (cardWarningVisibility) {
                marginModifierWarning = 60
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.84F)
                        .height(110.dp)
                        .padding(top = 7.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF2FCFD)
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(0.92f)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.warning
                            ),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = null,
                            modifier = Modifier
                                .width(22.dp)
                                .height(18.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.title_warning),
                            style = TextStyle(
                                fontFamily = MontserratFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 0.15.sp,
                            ),
                            modifier = Modifier
                                .padding(start = 15.dp),
                            color = Color(0xFF33C3D6)
                        )

                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.clear_blue_bni),
                            contentDescription = null,
                            modifier = Modifier
                                .width(15.dp)
                                .clickable {
                                    cardWarningVisibility = false
                                }
                        )
                    }

                    val annotatedLinkString = buildAnnotatedString {
                        append("Jangan berikan data pribadi seperti: ")
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Kode OTP, PIN, dan password ")
                        }
                        append(
                            "kepada siapapun termasuk pihak BNI." +
                                    " Bila curiga akan terjadinya penipuan, segera hubungi BNI"
                        )
                        append("\nCall 1500046 untuk melaporkan.")

                    }

                    Text(
                        text = annotatedLinkString,
                        style = TextStyle(
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp,
                            letterSpacing = 0.15.sp,
                        ),
                        modifier = Modifier
                            .padding(start = 10.dp, top = 6.dp, end = 10.dp),
                        color = Color(0xFF33C3D6),
                        lineHeight = 15.sp
                    )
                }
            } else {
                marginModifierWarning = 170
            }

            Spacer(modifier = Modifier.padding(top = marginModifierWarning.dp))

            Image(
                painter = painterResource(
                    id = R.drawable.chat_us
                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .width(72.dp)
                    .height(56.dp)
                    .padding(end = 14.dp)
                    .align(Alignment.End)
                    .offset {
                        IntOffset(offsetX.roundToInt(), offsetY.roundToInt())
                    }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )

            Spacer(modifier = Modifier.padding(top = 62.dp))

            Text(
                text = "V 5.9.4",
                modifier = Modifier
                    .padding(end = 20.dp, top = 25.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    color = Color(0xFFFFFFFF),
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 0.15.sp,
                    textAlign = TextAlign.End
                ),
                lineHeight = 15.sp
            )

            if (loginStatus) {
                LoginDialog(onDismiss = { loginStatus = false }, onClickLogin = onContinue)
            }
            if (datePickerStatus) {
                datePickerDialog(onDismiss = { datePickerStatus = false })
            }
            if (ratingBarStatus) {
                RatingDialog(onDismiss = { ratingBarStatus = false })
            }
            if (dragBarStatus) {
                DragDialog(onDismiss = { dragBarStatus = false })
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TrialJetpackComposeTheme {
        WelcomeScreen(onContinue = { /*TODO*/ })
    }
}


enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}