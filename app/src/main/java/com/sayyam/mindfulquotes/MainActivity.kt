package com.sayyam.mindfulquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import com.sayyam.mindfulquotes.presentation.QuotesApp
import com.sayyam.mindfulquotes.ui.theme.MindfulQuotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindfulQuotesTheme {
                Surface(tonalElevation = 5.dp) {
                    QuotesApp()
                }
            }
        }
    }
}
