package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class PlusMinusState(val number: Int = 0, val clickedTimes: Int = 0)

class PlusMinusViewModel : ViewModel() {
    private val _plusMinusState = mutableStateOf(PlusMinusState())
    val plusMinusState: State<PlusMinusState> = _plusMinusState

    fun plus() {
        _plusMinusState.value = _plusMinusState.value.copy(
            number = _plusMinusState.value.number + 1,
            clickedTimes = _plusMinusState.value.clickedTimes + 1
        )
    }

    fun minus() {
        _plusMinusState.value = _plusMinusState.value.copy(
            number = _plusMinusState.value.number - 1,
            clickedTimes = _plusMinusState.value.clickedTimes + 1
        )
    }
}