package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.round

data class BMIState(val weight: String = "0.0", val height: String = "0.0", val  bmi: String = "")

class BMIViewModel: ViewModel() {
    private val _bmiState = mutableStateOf(BMIState())
    val bmiState: State<BMIState> = _bmiState

    fun calculate() {
        val height = _bmiState.value.height.toFloatOrNull() ?: 1.0f
        val weight = _bmiState.value.weight.toFloatOrNull() ?: 1.0f

        val bmi = round(weight / (height.pow(2)))
        _bmiState.value = _bmiState.value.copy(bmi = bmi.toString())
    }

    fun setW(w: String) {
        _bmiState.value = _bmiState.value.copy(weight = w)
    }

    fun setH(h: String) {
        _bmiState.value = _bmiState.value.copy(height = h)
    }
}