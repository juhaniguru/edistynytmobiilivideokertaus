package com.example.androidvideokertaus

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class PostState(
    val loading: Boolean = true,
    val comments: List<CommentItem> = emptyList(),
    val err: String? = null
)

class PostViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _id = savedStateHandle.get<String>("id")?.toIntOrNull() ?: 0
    private val _postState = mutableStateOf(PostState())
    val postState: State<PostState> = _postState

    init {
        Log.d("juhani", "postViewModel init")
        getComments()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("juhani", "Postviewmodel cleared")
    }

    private fun getComments() {
        viewModelScope.launch {
            try {
                _postState.value = _postState.value.copy(loading = true)
                val comments = postsService.getComments(_id)
                _postState.value = _postState.value.copy(comments = comments)
            } catch(e: Exception) {
                _postState.value = _postState.value.copy(err = e.toString())
            } finally {
                _postState.value = _postState.value.copy(loading = false)
            }
        }
    }
}