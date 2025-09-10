package com.nabila.userpjs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.nabila.userpjs.ui.navigation.AppNavGraph
import com.nabila.userpjs.ui.main.MainViewModel
import com.nabila.userpjs.ui.theme.UserPJSTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UserPJSTheme {
                AppNavGraph(mainViewModel = mainViewModel, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
