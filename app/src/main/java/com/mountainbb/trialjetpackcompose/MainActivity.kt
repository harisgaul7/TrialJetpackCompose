package com.mountainbb.trialjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrialJetpackComposeTheme {
                Router()
            }
        }
    }
}