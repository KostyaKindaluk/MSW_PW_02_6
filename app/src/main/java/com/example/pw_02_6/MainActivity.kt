package com.example.pw_02_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pw_02_6.screens.FormScreen
import com.example.pw_02_6.ui.theme.PW_02_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PW_02_6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}