package com.mountainbb.trialjetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.MontserratFontFamily
import kotlin.math.roundToInt


@Composable
fun DragDialog(onDismiss: () -> Unit) {
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


                var textData by remember { mutableStateOf("disini") }

                Text(
                    text = textData,
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

                var widthBox by remember { mutableStateOf(0.dp) }
                var heightBox by remember { mutableStateOf(0.dp) }
                var roundBox by remember { mutableStateOf(50.dp) }
                var roundFullBox by remember { mutableStateOf(0.dp) }
                val offsetX = remember { mutableStateOf(0f) }
                val offsetY = remember { mutableStateOf(0f) }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.White, shape = RoundedCornerShape(50.dp))
                ){
                    Box(
                        Modifier
                            .offset {
                                IntOffset(
                                    offsetX.value.roundToInt(),
                                    offsetY.value.roundToInt()
                                )
                            }
                            .background(Color.Black, shape = RoundedCornerShape(50.dp))
                            .fillMaxWidth()
                            .size(60.dp)
                    )
                    Box(modifier = Modifier
                            .offset {
                                IntOffset(
                                    offsetX.value.roundToInt(),
                                    offsetY.value.roundToInt()
                                )
                            }
                            .align(Alignment.CenterStart)
                            .background(Color.Blue, shape = RoundedCornerShape(topStart = roundBox, bottomStart = roundBox, topEnd = roundFullBox, bottomEnd = roundFullBox))
                            .width(widthBox)
                            .size(heightBox)
                            .pointerInput(Unit) {
                                detectDragGestures (onDragEnd = {
                                    if (widthBox < 160.dp) {
                                        widthBox = 0.dp
                                    }
                                }
                                )
                                { change, dragAmount ->
                                    change.consume()

                                    offsetX.value = (offsetX.value + dragAmount.x)
                                        .coerceIn(0f, 0f)
                                    heightBox += (dragAmount.x.toDp()*2)
                                    widthBox += dragAmount.x.toDp()

                                    if (widthBox <20.dp) {
                                        roundBox += (dragAmount.x.toDp()/4)
                                    }

                                    if (widthBox > 190.dp) {
                                        roundFullBox += dragAmount.x.toDp()
                                    }
                                    else {
                                        roundFullBox = 0.dp
                                    }

                                    offsetY.value = (offsetY.value + dragAmount.y)
                                        .coerceIn(0f, 0f)
                                }
                            }
                    )
                    Text(
                        "Tetap tahan untuk konfirmasi",
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = Color.White
                    )
                }
            }
        }
    }
}