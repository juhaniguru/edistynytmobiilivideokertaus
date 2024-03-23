package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class LogoutState(
    val loading: Boolean = false,
    val err: String? = null,
    val logoutOk: Boolean = false
)

class LandingScreenViewModel(private val db: AccountDatabase = DbProvider.db) : ViewModel() {

    private val _logoutState = mutableStateOf(LogoutState())
    val logoutState: State<LogoutState> = _logoutState

    fun setLogout(ok: Boolean) {
        _logoutState.value = _logoutState.value.copy(logoutOk = ok)
    }

    fun logout() {
        viewModelScope.launch {
            try {
                _logoutState.value = _logoutState.value.copy(loading = true)
                val accessToken = db.accountDao().getToken()
                // if(accessToken != null)
                accessToken?.let {
                    authService.logout("Bearer $it")
                    db.accountDao().removeTokens()
                    setLogout(true)
                }

            } catch (e: Exception) {
                _logoutState.value = _logoutState.value.copy(err = e.toString())
            } finally {
                _logoutState.value = _logoutState.value.copy(loading = false)
            }
        }
    }
}