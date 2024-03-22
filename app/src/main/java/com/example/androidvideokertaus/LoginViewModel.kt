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
    val done: Boolean = false
)

data class LoginReq(
    val username: String = "",
    val password: String = ""
)

data class LoginRes(
    @SerializedName("access_token")
    val accessToken: String = ""
)

class LoginViewModel(
    private val db: AccountDatabase = DbProvider.db
) : ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        checkAccount()
    }

    fun checkAccount() {
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(loading = true)
                val token = db.accountDao().getToken()
                token?.let {
                    authService.getAccount("Bearer $it")
                    setDone(true)
                }
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(err = e.toString())
            } finally {
                _loginState.value = _loginState.value.copy(loading = false)
            }
        }
    }

    fun setUsername(u: String) {
        _loginState.value = _loginState.value.copy(username = u)
    }

    fun setPassword(p: String) {
        _loginState.value = _loginState.value.copy(password = p)
    }

    fun setDone(done: Boolean) {
        _loginState.value = _loginState.value.copy(done = done)
    }

    fun login() {
        Log.d("juhani", "login")
        viewModelScope.launch {
            try {
                _loginState.value = _loginState.value.copy(loading = true)
                val res = authService.login(
                    LoginReq(
                        username = _loginState.value.username,
                        password = _loginState.value.password
                    )
                )
                db.accountDao().addToken(AccountEntity(accessToken = res.accessToken))
                setDone(true)
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(err = e.toString())
            } finally {
                _loginState.value = _loginState.value.copy(loading = false)
            }
        }
    }
}