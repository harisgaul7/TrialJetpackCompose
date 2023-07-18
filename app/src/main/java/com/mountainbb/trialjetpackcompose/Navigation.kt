package com.mountainbb.trialjetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mountainbb.trialjetpackcompose.Destinations.LOGIN
import com.mountainbb.trialjetpackcompose.Destinations.TRANSFER
import com.mountainbb.trialjetpackcompose.component.datePickerDialog
import com.mountainbb.trialjetpackcompose.transfer.TransferScreen
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
            TransferScreen()
        }
        composable(TRANSFER) {
            WelcomeScreen(
                onContinue = {
                    router.navigate(TRANSFER)
                }
            )
        }
    }
}