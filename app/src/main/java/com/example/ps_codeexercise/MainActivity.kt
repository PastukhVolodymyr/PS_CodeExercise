package com.example.ps_codeexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ps_codeexercise.ui.scope.driverscreen.DriverScreen
import com.example.ps_codeexercise.ui.theme.PS_CodeExerciseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PS_CodeExerciseTheme {
                DriverScreen()
            }
        }
    }
}