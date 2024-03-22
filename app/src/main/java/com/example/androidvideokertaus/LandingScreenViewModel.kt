package com.example.androidvideokertaus

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class IsLoggedIn(
    val loading: Boolean = false,
    val err: String? = null,
    val loggedIn: Boolean = false,
    val done: Boolean = false
)

class LandingScreenViewModel(private val db: AccountDatabase = DbProvider.db) : ViewModel() {

    private val _isLoggedIn = mutableStateOf(IsLoggedIn())
    private lateinit var _token: String
    val isLoggedIn: State<IsLoggedIn> = _isLoggedIn

    init {
        getToken()
    }

    fun clearErr() {
        _isLoggedIn.value  = _isLoggedIn.value.copy(err = null)
    }

    fun setDone(done: Boolean) {
        _isLoggedIn.value = _isLoggedIn.value.copy(done = done)
    }


    fun logout() {
        viewModelScope.launch {
            try {
                _isLoggedIn.value = _isLoggedIn.value.copy(loading = true)
                authService.logout(_token)
                db.accountDao().removeToken()
                setDone(true)
            } catch (e: Exception) {
                _isLoggedIn.value = _isLoggedIn.value.copy(err = e.toString())
            } finally {
                _isLoggedIn.value = _isLoggedIn.value.copy(loading = false)
            }
        }
    }

    private fun getToken() {
        viewModelScope.launch {
            try {
                _isLoggedIn.value = _isLoggedIn.value.copy(loading = true)
                val token = db.accountDao().getToken()
                token?.let {
                    _token = token
                    _isLoggedIn.value = _isLoggedIn.value.copy(loggedIn = true)

                }
            } catch (e: Exception) {
                _isLoggedIn.value = _isLoggedIn.value.copy(err = e.toString())
                _token = ""
            } finally {
                _isLoggedIn.value = _isLoggedIn.value.copy(loading = false)
            }
        }
    }

}