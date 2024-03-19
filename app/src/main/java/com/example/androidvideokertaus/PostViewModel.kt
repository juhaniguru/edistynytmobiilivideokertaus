package com.example.androidvideokertaus

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class CommentItem(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val body: String = "",
    val postId: Int = 0
)

data class PostState(
    val comments: List<CommentItem> = emptyList(),
    val loading: Boolean = false,
    val err: String? = null
)

class PostViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _postState = mutableStateOf(PostState())
    val postState: State<PostState> = _postState
    private val _postId = savedStateHandle.get<String>("id")?.toIntOrNull() ?: 0

    init {
        getComemnts()
    }

    private fun getComemnts() {
        viewModelScope.launch {
            try {
                _postState.value = _postState.value.copy(loading = true)
               val comments = postsService.getComments(_postId)
              _postState.value = _postState.value.copy(comments=comments)
            } catch(e: Exception) {
                _postState.value = _postState.value.copy(err = e.toString())
            } finally {
                _postState.value = _postState.value.copy(loading = false)
            }
        }
    }
}