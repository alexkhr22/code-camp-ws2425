package com.uebung_basics.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


//Navigation Bar Items
@Composable
fun BottomNavigationBar(navController: NavHostController){
    NavigationBar{
        NavigationBarItem(
            selected = navController.currentDestination?.route == "home",
            onClick = {
                if (navController.currentDestination?.route != "home") {
                    navController.navigate("home")
                }
            },
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == "profile",
            onClick = {
                if (navController.currentDestination?.route != "profile") {
                    navController.navigate("profile")
                }
            },
            label = { Text("Profile") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Profile") }
        )

        NavigationBarItem(
            selected = navController.currentDestination?.route == "settings",
            onClick = {
                if (navController.currentDestination?.route != "settings") {
                    navController.navigate("settings")
                }
            },
            label = { Text("Settings") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Settings") }
        )

    }
}