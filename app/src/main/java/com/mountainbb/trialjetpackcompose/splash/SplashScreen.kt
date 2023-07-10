package com.mountainbb.trialjetpackcompose.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mountainbb.trialjetpackcompose.R
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import com.mountainbb.trialjetpackcompose.util.supportWideScreen

@Composable
fun SplashScreen() {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.splash),
                contentScale = ContentScale.Crop
            )) {
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    TrialJetpackComposeTheme {
        SplashScreen()
    }
}