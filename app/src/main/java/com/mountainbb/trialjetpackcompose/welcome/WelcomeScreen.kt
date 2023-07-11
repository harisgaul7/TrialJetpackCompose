package com.mountainbb.trialjetpackcompose.welcome

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusRequester
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.component.InputTypeClass
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.SeptimusFontFamily
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.supportWideScreen
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
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
    var popupStatus by remember {
        mutableStateOf(false)
    }
    val focusRequester = remember { FocusRequester() }

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
                    contentScale = ContentScale.Fit,
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
                        .height(70.dp)
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
                )
            }

            Spacer(
                modifier = Modifier
                    .height(18.dp)
            )

            Button(
                onClick = {
                    popupStatus = !popupStatus
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

                var isTimerActive by remember {
                    mutableStateOf(true)
                }

                LaunchedEffect(key1 = isTimerActive) {
                    if (isTimerActive) {
                        var second = 0
                        while (second >= 0) {
                            if (second % 2 == 1) {
                                resourceImages = R.drawable.face_recognation
                            } else {
                                resourceImages = R.drawable.fingerprint
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
                onClick = onContinue,
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
                onClick = onContinue,
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

            if (popupStatus) {
                DatePickerDialog(onDismiss = { popupStatus = false }) {

                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TrialJetpackComposeTheme {
        DatePickerDialog(onDismiss = { /*TODO*/ }) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(Color(0xFFEAF1F1), shape = RoundedCornerShape(15.dp))
        ) {
            val date = listOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31"
            )
            val month = listOf(
                "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
                "September", "Oktober", "November", "Desember"
            )
            val year = listOf(
                "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909",
                "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919",
                "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929",
                "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939",
                "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949",
                "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959",
                "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969",
                "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979",
                "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
                "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999",
                "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
                "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039",
                "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049",
                "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059",
                "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069",
                "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079",
                "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089",
                "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099",
                "2100"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.title_welcome),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 28.dp),
                    color = Color(0xFF6B6A6A)
                )
                LazyColumn {
                    itemsIndexed(date) { index, item ->
                        if (index % 2 == 0) {
                            Text(text = item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginDialog(onDismiss: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        var saveUserStateButton by remember {
            mutableStateOf(true)
        }
        var imageUserStateButton by remember {
            mutableStateOf(R.drawable.checkbox_on)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .background(Color.White, shape = RoundedCornerShape(5.dp))
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.clear_gray),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 10.dp)
                        .width(15.dp)
                        .clickable {
                            onDismiss()
                        },
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.title_welcome),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.15.sp,
                    ),
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 28.dp),
                    color = Color(0xFF6B6A6A)
                )

                TextFieldWithLabel(
                    labelTextField = stringResource(id = R.string.title_user_id),
                    value = "*****ama",
                    enabled = false
                )

                Spacer(modifier = Modifier.size(5.dp))

                TextFieldWithLabel(
                    labelTextField = stringResource(id = R.string.title_mpin),
                    inputType = InputTypeClass.Password,
                    maxChar = 6
                )

                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 20.dp),
                ) {
                    if (saveUserStateButton) {
                        imageUserStateButton = R.drawable.checkbox_on
                    } else {
                        imageUserStateButton = R.drawable.checkbox_off
                    }
                    Image(
                        painter = painterResource(id = imageUserStateButton),
                        contentDescription = "Unchecked",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 12.dp)
                            .clickable {
                                saveUserStateButton = !saveUserStateButton
                            }
                    )

                    Text(
                        text = stringResource(id = R.string.title_save_user_id),
                        style = TextStyle(
                            fontFamily = MontserratFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 0.15.sp,
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        color = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(id = R.string.title_forgot_user_id),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Color(0xFF1273A7)
                )

                Spacer(
                    modifier = Modifier
                        .weight(1F)
                )

                Text(
                    text = stringResource(id = R.string.title_forgot_mpin),
                    style = TextStyle(
                        fontFamily = MontserratFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 0.15.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Color(0xFF1273A7)
                )
            }

            Button(
                onClick = {},
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
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.25.sp,
                    ),
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun CustomCheckbox(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    IconButton(modifier = modifier, onClick = { onCheckedChange(!checked) }) {
        if (checked) {

        }

        //the box image frame unchecked
        Image(
            painter = painterResource(id = R.drawable.checkbox_off),
            contentDescription = "Unchecked"
        )
        AnimatedVisibility(
            modifier = modifier,
            visible = checked,
            exit = shrinkOut(shrinkTowards = Alignment.TopStart) + fadeOut()
        ) {
            //the check only (without the surrounding box)
            Image(
                painter = painterResource(id = R.drawable.checkbox_on),
                contentDescription = "Checked"
            )
        }
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