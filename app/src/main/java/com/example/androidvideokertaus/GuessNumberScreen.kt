package com.example.androidvideokertaus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GuessNumberScreen() {

    val vm: GuessNumberViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(vm.guessNumberState.value.color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(vm.guessNumberState.value.randomNumber, style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(value = vm.guessNumberState.value.textFieldValue, onValueChange = {
            vm.setValue(it)
        })
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { vm.newGame() }) {
                Text(text = "New Game")
            }
            Button(
                enabled = vm.guessNumberState.value.randomNumber != "",
                onClick = {
                    vm.setGuess(vm.guessNumberState.value.textFieldValue)
                }) {
                Text(text = "Guess")
            }
        }
            when(vm.guessNumberState.value.showGuessCount) {
                true -> Text("${vm.guessNumberState.value.guessCount} kerta toden sanoo!")
                false -> LazyColumn() {
            }
        }
    }


}