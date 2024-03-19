package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class PlusMinusState(val timesClicked: Int = 0, val number: Int = 0)

class PlusMinusViewModel : ViewModel() {

    private val _plusMinusState = mutableStateOf(PlusMinusState())
    val plusMinusState: State<PlusMinusState> = _plusMinusState

    fun plus() {
        _plusMinusState.value = _plusMinusState.value.copy(
            number = _plusMinusState.value.number + 1,
            timesClicked = _plusMinusState.value.timesClicked + 1
        )

    }

    fun minus() {
        _plusMinusState.value = _plusMinusState.value.copy(
            number = _plusMinusState.value.number - 1,
            timesClicked = _plusMinusState.value.timesClicked + 1
        )
    }


}