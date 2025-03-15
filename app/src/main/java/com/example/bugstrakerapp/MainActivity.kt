package com.example.bugstrakerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bugstrakerapp.model.DataStoreManeger
import com.example.bugstrakerapp.ui.avtorization.ScreenAvtorization
import com.example.bugstrakerapp.ui.bugslist.BugsList
import com.example.bugstrakerapp.ui.theme.BugsTrakerAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BugsTrakerAppTheme {
                val manegerDataStore = DataStoreManeger(context = this)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "avtorization") {
                    composable("avtorization") {
                        ScreenAvtorization(manegerDataStore)
                    }
                    composable("buglist") {
                        BugsList()
                    }
                }
            }
        }
    }
}
