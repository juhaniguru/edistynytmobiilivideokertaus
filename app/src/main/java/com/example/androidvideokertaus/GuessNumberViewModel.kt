package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

data class GuessNumberState(
    val guess: String = "",
    val randomNumber: String = "",
    val textFieldValue: String = "",
    val guessCount: Int = 0,
    val showGuessCount: Boolean = false,
    val color: Color = Color.White
)

class GuessNumberViewModel : ViewModel() {

    private val _guessNumberState = mutableStateOf(GuessNumberState())
    val guessNumberState: State<GuessNumberState> = _guessNumberState

    private fun isNumber(x: String): Boolean {
        return x.toIntOrNull() != null
    }

    fun newGame() {
        _guessNumberState.value =
            _guessNumberState.value.copy(
                randomNumber = (0..10).random().toString(),
                color = Color.White,
                guess = "",
                guessCount = 0,
                textFieldValue = "",
                showGuessCount = false
            )
    }

    fun setValue(newValue: String) {
        _guessNumberState.value = guessNumberState.value.copy(textFieldValue = newValue)
    }

    fun setGuess(userGuess: String) {
        var color = Color.White

        if (isNumber(userGuess)) {
            val userGuessInt = userGuess.toInt()
            val randomNumberInt = _guessNumberState.value.randomNumber.toInt()
            if (userGuessInt < randomNumberInt) {
                color = Color.Blue
            } else if (userGuessInt > randomNumberInt) {
                color = Color.Red
            } else {
                color = Color.Green
                _guessNumberState.value =
                    _guessNumberState.value.copy(randomNumber = "", showGuessCount = true)
            }
            _guessNumberState.value = _guessNumberState.value.copy(
                guess = userGuess,
                guessCount = _guessNumberState.value.guessCount + 1,
                color = color
            )
        }
    }
}

