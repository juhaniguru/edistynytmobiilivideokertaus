package com.example.androidvideokertaus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LandingScreen(goToGuessNumber: () -> Unit, goToPlusMinus : () -> Unit, goToPosts: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { goToGuessNumber() }) {
            Text("Guess Number")
        }
        Button(onClick = { goToPlusMinus() }) {
            Text("Plus & Minus")
        }

        Button(onClick = { goToPosts() }) {
            Text("Posts")
        }
    }
}