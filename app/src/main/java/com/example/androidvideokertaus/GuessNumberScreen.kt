package com.example.androidvideokertaus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuessNumberScreen() {

    val vm: GuessNumberViewModel = viewModel()

    Column(
        modifier = Modifier.fillMaxSize().background(color = vm.guessNumberState.value.color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = vm.guessNumberState.value.number
        )

        OutlinedTextField(value = vm.guessNumberState.value.guess, onValueChange = {
            vm.setGuess(it)
        })
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { vm.startGame() }) {
                Text(text = "New Game")
            }
            Button(
                onClick = { vm.guessNumber() },
                enabled = vm.guessNumberState.value.guess != "" && vm.guessNumberState.value.number != ""
            ) {
                Text(text = "Guess")
            }
        }
    }
}