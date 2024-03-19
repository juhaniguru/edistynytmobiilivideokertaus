package com.example.androidvideokertaus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(goToPostScreen : (Int) -> Unit) {

    val vm: PostsViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                },
                title = { Text("Posts") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when {
                vm.postsState.value.loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                vm.postsState.value.err != null -> Text("Virhe: ${vm.postsState.value.err}")
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn() {
                            items(vm.postsState.value.posts) {
                                PostListItem(item = it, goToPostScreen)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostListItem(item: PostItem, goToPostScreen: (Int) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier.clickable {
                goToPostScreen(item.id)
            },
            text=item.title, style = MaterialTheme.typography.headlineMedium)
        Text(item.body, fontStyle = FontStyle.Italic, style = MaterialTheme.typography.bodySmall)
    }
}