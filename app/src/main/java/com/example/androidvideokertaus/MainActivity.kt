package com.example.androidvideokertaus

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidvideokertaus.ui.theme.AndroidVideoKertausTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AndroidVideoKertausTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "loginScreen") {
                        composable("landingScreen") {
                            LandingScreen(goToGuessNumber = {
                                navController.navigate("guessNumberScreen")
                            }, goToPlusMinus = {
                                navController.navigate("plusMinusScreen")
                            }, goToPostsScreen = {
                                navController.navigate("postsScreen")
                            })
                        }

                        composable("guessNumberScreen") {
                            GuessNumberScreen()
                        }

                        composable("plusMinusScreen") {
                            PlusMinusScreen(goToLandingScreen = {
                                navController.navigate("landingScreen")
                            })
                        }

                        composable("postsScreen") {
                            PostsScreen(goToPostScreen = {postId ->
                                navController.navigate("postScreen/$postId")
                            })
                        }

                        composable("postScreen/{id}") {
                            PostScreen()
                        }

                        composable("loginScreen") {
                            LoginScreen()
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, age: Int, callbackFunction : () -> Unit) {
    Text(
        text = "Hello my name is $name and I am $age years old"
    )
    Button(onClick = { callbackFunction() }) {
        Text("Click me")
    }
}

