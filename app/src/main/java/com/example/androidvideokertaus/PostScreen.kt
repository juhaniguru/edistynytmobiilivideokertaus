package com.example.androidvideokertaus

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
fun PostScreen() {
    val vm: PostViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                    }
                },
                title = { Text("Post") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when {
                vm.postState.value.loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                vm.postState.value.err != null -> Text("Virhe: ${vm.postState.value.err}")
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LazyColumn() {
                            items(vm.postState.value.comments) {
                                CommentListItem(item = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommentListItem(item: CommentItem) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(item.name, style=MaterialTheme.typography.headlineMedium)
        Text(text = item.body, style=MaterialTheme.typography.bodySmall)
        Text(
            modifier = Modifier.align(Alignment.End),
            text=item.email, fontStyle = FontStyle.Italic)
    }
}