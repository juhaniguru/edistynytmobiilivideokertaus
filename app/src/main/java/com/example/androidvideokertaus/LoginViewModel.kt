package com.example.androidvideokertaus

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch

data class LoginState(
    val loading: Boolean = false,
    val err: String? = null,
    val username: String = "",
    val password: String = ""
)

data class AuthReq(val username: String = "", val password: String = "")
data class AuthRes(
    @SerializedName("access_token")
    val accessToken: String = ""
)

class LoginViewModel : ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    fun setUsername(username: String) {
        _loginState.value = _loginState.value.copy(username = username)
    }

    fun setPassword(pwd: String) {
        _loginState.value = _loginState.value.copy(password = pwd)
    }


    fun login() {
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(loading = true)
                val res = authService.login(
                    AuthReq(
                        username = _loginState.value.username,
                        password = _loginState.value.password
                    )
                )

            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(err = e.toString())
            } finally {
                _loginState.value = _loginState.value.copy(loading = false)
            }
        }
    }


}