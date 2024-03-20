package com.example.androidvideokertaus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen() {

    val vm: BMIViewModel = viewModel()

    Scaffold(topBar = { TopAppBar(title = { Text("BMI") }) }) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.padding(16.dp)


                ) {
                    OutlinedTextField(
                        modifier = Modifier.padding(end = 8.dp),
                        value = vm.bmiState.value.weight,
                        onValueChange = {
                            vm.setW(it)
                        },
                        placeholder = { Text("Paino / kg") })
                    OutlinedTextField(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        value = vm.bmiState.value.height,
                        onValueChange = {
                            vm.setH(it)
                        },
                        placeholder = { Text("Pituus / m") })
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { vm.calculate() }) {
                    Text(text = "Calculate")
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "BMI: ${vm.bmiState.value.bmi}",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}