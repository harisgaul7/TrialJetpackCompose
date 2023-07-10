package com.mountainbb.trialjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mountainbb.trialjetpackcompose.Destinations.LOGIN
import com.mountainbb.trialjetpackcompose.Destinations.TRANSFER
import com.mountainbb.trialjetpackcompose.component.TextFieldWithLabel
import com.mountainbb.trialjetpackcompose.login.LoginCard
import com.mountainbb.trialjetpackcompose.login.LoginScreen
import com.mountainbb.trialjetpackcompose.welcome.WelcomeScreen

object Destinations {
    const val LOGIN = "login"
    const val TRANSFER = "transfer"
}

@Preview
@Composable
fun Router(
    router: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    NavHost(navController = router, startDestination = LOGIN) {
        composable(LOGIN) {
            WelcomeScreen(
                onContinue = {
                    router.navigate(TRANSFER)
                }
            )
        }
        composable(TRANSFER) {
            LoginCard()
        }
    }
}