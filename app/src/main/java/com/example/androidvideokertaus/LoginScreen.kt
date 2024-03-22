package com.example.androidvideokertaus

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(alreadyLoggedIn: () -> Unit) {

    val vm: LoginViewModel = viewModel()

    LaunchedEffect(key1 = vm.loginState.value.done) {
        if(vm.loginState.value.done) {
            vm.setDone(false)
            alreadyLoggedIn()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Login") })
        }
    ) { padding ->
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

                vm.loginState.value.err != null -> Text("Virhe: ${vm.loginState.value.err}")
                else -> {
                    Column(

                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(value = vm.loginState.value.username, onValueChange = {
                            vm.setUsername(it)
                        })
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(value = vm.loginState.value.password, onValueChange = {
                            vm.setPassword(it)
                        })
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { vm.login() }) {
                            Text(text = "Login")
                        }
                    }
                }
            }
        }
    }
}