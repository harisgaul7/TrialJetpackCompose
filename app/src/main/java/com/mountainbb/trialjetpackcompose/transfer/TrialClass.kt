package com.mountainbb.trialjetpackcompose.transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme

@Composable
fun TrialScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

    }
}

@Preview
@Composable
fun PreviewTrialScreen() {
    TrialJetpackComposeTheme {
        TrialScreen()
    }
}
