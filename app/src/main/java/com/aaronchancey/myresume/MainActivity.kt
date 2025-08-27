package com.aaronchancey.myresume

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aaronchancey.myresume.presentation.app.MyResumeApp
import com.aaronchancey.myresume.ui.theme.MyResumeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyResumeTheme {
                MyResumeApp()
            }
        }
    }
}
