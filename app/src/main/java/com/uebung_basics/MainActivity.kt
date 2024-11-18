package com.uebung_basics

import AppWorker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uebung_basics.ui.theme.Uebung_basicsTheme
import androidx.navigation.compose.rememberNavController
import com.uebung_basics.ui.view.*
import androidx.navigation.compose.composable
import androidx.room.Room
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.uebung_basics.data.database.DrawnCardDataBase
import com.uebung_basics.ui.navigation.BottomNavigationBar
import com.uebung_basics.ui.viewmodel.DeckViewModel
import java.util.concurrent.TimeUnit


/*
- view package in ui reinpacken
- navigation package in ui reinpacken
- für navigation bar dependecys in .grandle.kts (Module:app) hinzufügen link: https://developer.android.com/jetpack/androidx/releases/navigation?hl=de#kts
 */

class MainActivity : ComponentActivity() {
   private val database by lazy {
         Room.databaseBuilder(
            applicationContext,
            DrawnCardDataBase::class.java,
            "drawn_card_database"
        ).build()
    }

    // Lazy-Initialisierung des CardViewModels mit der Datenbank


    private val viewModel: DeckViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Uebung_basicsTheme {
                MainScreen()
            }
        }

        startAppWorker()
    }

    private fun startAppWorker() {
        // Definiere eine periodische Arbeitsanforderung, die alle 15 Minuten ausgeführt wird.
        // WorkManager unterstützt ein Minimum von 15 Minuten bei PeriodicWorkRequest.
        val workRequest = PeriodicWorkRequest.Builder(
            AppWorker::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        // Plane den Worker
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    //Bottomnavigationbar wird dem Scaffold hinzugefügt
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHostContainer(navController, innerPadding)
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, innerPadding: PaddingValues){
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(innerPadding)
    ) {
        //Routen für bottom navigation bar wird hier festgelegt
        composable("home") { HomeView() }
        composable("profile") { ProfileView() }
        composable("settings") { SettingsView()}
    }
}
