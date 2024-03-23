package com.example.androidvideokertaus

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LandingScreen(
    goToGuessNumber: () -> Unit,
    goToPlusMinus: () -> Unit,
    goToPostsScreen: () -> Unit,
    goToLoginScreen: () -> Unit
) {

    val vm: LandingScreenViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(key1 = vm.logoutState.value.err) {
        vm.logoutState.value.err?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(key1 = vm.logoutState.value.logoutOk) {
        if (vm.logoutState.value.logoutOk) {
            vm.setLogout(false)
            goToLoginScreen()
        }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { goToGuessNumber() }) {
            Text("Guess Number")
        }
        Button(onClick = { goToPlusMinus() }) {
            Text("Plus & Minus")
        }

        Button(onClick = { goToPostsScreen() }) {
            Text(text = "Posts")
        }

        Button(onClick = { vm.logout() }) {
            Text(text = "Logout")
        }


    }
}