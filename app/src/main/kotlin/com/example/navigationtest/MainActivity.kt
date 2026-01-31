package com.example.navigationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.navigationtest.core.extension.LoggingBackStacks
import com.example.navigationtest.core.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController().apply { LoggingBackStacks() }
            AppTheme {
                AppNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                )
            }
        }
    }
}
