package com.mountainbb.trialjetpackcompose.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

fun Modifier.supportWideScreen() = this
    .fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .widthIn(max = 840.dp)


@Composable
fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    return@composed then(clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    ))
}