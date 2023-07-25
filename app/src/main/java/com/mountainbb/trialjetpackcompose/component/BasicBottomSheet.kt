package com.mountainbb.trialjetpackcompose.component

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout() {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    var isSheetFullScreen by remember { mutableStateOf(false) }
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val modifier = if (isSheetFullScreen)
        Modifier
            .fillMaxSize()
    else
        Modifier.fillMaxWidth()

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        isSheetFullScreen = !isSheetFullScreen
                    }
                ) {
                    Text(text = "Toggle Sheet Fullscreen")
                }

                Button(
                    onClick = {
                        coroutineScope.launch { modalSheetState.hide() }
                    }
                ) {
                    Text(text = "Hide Sheet")
                }
            }
        }
    ) {
        coroutineScope.launch {
            modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheet() {
    TrialJetpackComposeTheme {
        BottomSheetLayout()
    }
}