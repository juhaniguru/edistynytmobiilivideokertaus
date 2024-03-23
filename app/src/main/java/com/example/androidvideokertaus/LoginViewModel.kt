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
    val password: String = "",
    val loginOk: Boolean = false
)

data class AuthReq(val username: String = "", val password: String = "")
data class AuthRes(
    @SerializedName("access_token")
    val accessToken: String = ""
)

class LoginViewModel(private val db: AccountDatabase = DbProvider.db) : ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    fun setUsername(username: String) {
        _loginState.value = _loginState.value.copy(username = username)
    }

    fun setLogin(ok: Boolean) {
        _loginState.value = _loginState.value.copy(loginOk = ok)
    }

    fun setPassword(pwd: String) {
        _loginState.value = _loginState.value.copy(password = pwd)
    }

    init {
        // 1. hae tietokannasta accesstoken
        // jos se löytyy
        // 2. tee reqeust api/v1/auth/account-endpointiin (accessToken laitetaan request headeriin Authorization Bearer-tokenina)
        // jos olet kirjautunut sisään
        // 3. ohjaa automaattisesti landingScreeniin kirjautumatta uudelleen sisään (käytä tähän LaunchedEffectiä)
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
                db.accountDao().addToken(
                    AccountEntity(accessToken = res.accessToken)
                )
                setLogin(true)
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(err = e.toString())
            } finally {
                _loginState.value = _loginState.value.copy(loading = false)
            }
        }
    }


}