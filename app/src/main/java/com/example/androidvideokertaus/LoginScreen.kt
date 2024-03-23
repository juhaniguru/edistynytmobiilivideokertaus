package com.example.androidvideokertaus

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(goToLandingScreen : () -> Unit) {
    val vm: LoginViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(key1 = vm.loginState.value.err) {
        vm.loginState.value.err?.let {
            Toast.makeText(context, vm.loginState.value.err, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(key1 = vm.loginState.value.loginOk) {
        if(vm.loginState.value.loginOk) {
            vm.setLogin(false)
            goToLandingScreen()
        }
    }


    Scaffold(topBar = {
        TopAppBar(title = { Text("Login") })
    }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when {
                vm.loginState.value.loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )


                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = vm.loginState.value.username,
                            onValueChange = { username ->
                                vm.setUsername(username)
                            },
                            placeholder = { Text("Username") })
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = vm.loginState.value.password,
                            onValueChange = { password ->
                                vm.setPassword(password)
                            },
                            placeholder = { Text("Password") })

                        Button(onClick = { vm.login() }) {
                            Text("Login")

                        }
                    }
                }
            }
        }
    }
}