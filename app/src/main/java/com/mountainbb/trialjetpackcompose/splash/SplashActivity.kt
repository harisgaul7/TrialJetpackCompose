package com.mountainbb.trialjetpackcompose.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.mountainbb.trialjetpackcompose.MainActivity
import com.mountainbb.trialjetpackcompose.ui.theme.TrialJetpackComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrialJetpackComposeTheme {
                SplashScreen()
            }
        }
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}