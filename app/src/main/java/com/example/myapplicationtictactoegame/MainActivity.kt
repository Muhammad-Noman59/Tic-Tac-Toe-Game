package com.example.myapplicationtictactoegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationtictactoegame.presentation.GameScreen
import com.example.myapplicationtictactoegame.ui.theme.MyApplicationTicTacToeGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTicTacToeGameTheme {

                val  viewModel : GameViewModel by viewModels()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Box (
                       modifier = Modifier.fillMaxSize()
                           .padding(innerPadding)
                   ){
                       GameScreen(
                           viewModel = viewModel
                       )
                   }
                }
            }
        }
    }
}
