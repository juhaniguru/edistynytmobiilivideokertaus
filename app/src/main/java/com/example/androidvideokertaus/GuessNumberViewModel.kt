package com.example.androidvideokertaus

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

data class GuessNumberState(
    val number: String = "",
    val guessCount: Int = 0,
    val guess: String = "",
    val color: Color = Color.White

)

class GuessNumberViewModel : ViewModel() {
    private val _guessNumberState = mutableStateOf(GuessNumberState())
    val guessNumberState: State<GuessNumberState> = _guessNumberState

    fun startGame() {
        _guessNumberState.value =
            _guessNumberState.value.copy(
                number = (0..10).random().toString(),
                guessCount = 0,
                guess = "",
                color = Color.White

            )
    }

    fun setGuess(guess: String) {

        _guessNumberState.value = _guessNumberState.value.copy(guess = guess)
    }

    fun guessNumber() {
        var newColor = Color.Blue
        if (_guessNumberState.value.guess.toInt() > _guessNumberState.value.number.toInt()) {
            newColor = Color.Red
        } else if (_guessNumberState.value.guess.toInt() == _guessNumberState.value.number.toInt()) {
            newColor = Color.Green
        }

        _guessNumberState.value =
            _guessNumberState.value.copy(
                guessCount = _guessNumberState.value.guessCount + 1,
                color = newColor
            )
    }


}