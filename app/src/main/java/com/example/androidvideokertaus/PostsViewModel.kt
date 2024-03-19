package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


data class PostItem(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)

data class PostsState(
    val posts: List<PostItem> = emptyList(),
    val loading: Boolean = false,
    val err: String? = null
)

class PostsViewModel : ViewModel() {
    private val _postsState = mutableStateOf(PostsState())
    val postsState: State<PostsState> = _postsState

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                _postsState.value = _postsState.value.copy(loading = true)
                val res = postsService.getPosts()
                _postsState.value = _postsState.value.copy(posts = res)
            } catch(e: Exception) {
                _postsState.value = _postsState.value.copy(err = e.toString())
            } finally {
                _postsState.value = _postsState.value.copy(loading = false)
            }
        }
    }
}