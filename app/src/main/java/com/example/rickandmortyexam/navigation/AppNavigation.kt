package com.example.rickandmortyexam.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyexam.screens.api_rick.ApiRickScreen
import com.example.rickandmortyexam.screens.create_rick.CreateRickScreen
import com.example.rickandmortyexam.screens.api_rick.ApiRickViewModel
import com.example.rickandmortyexam.screens.create_rick.CreateRickViewModel
import com.example.rickandmortyexam.screens.show_rick.ShowRickScreen
import com.example.rickandmortyexam.screens.show_rick.ShowRickViewModel
import kotlinx.serialization.Serializable

@Serializable
object ApiRick

@Serializable
object ShowRick

@Serializable
object CreateRick

@Composable
fun AppNavigation(
    apiRickViewModel: ApiRickViewModel,
    createRickViewModel: CreateRickViewModel,
    showRickViewModel: ShowRickViewModel
) {
    val navController = rememberNavController()

    var chosenScreen by remember {
        mutableIntStateOf(1)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = chosenScreen == 0,
                    onClick = {
                        chosenScreen = 0
                        navController.navigate(ApiRick)},
                    icon = {
                        Icon(imageVector =
                        if (chosenScreen == 0)Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = { Text("API Rick") }
                )
                NavigationBarItem(
                    selected = chosenScreen == 1,
                    onClick = {
                        chosenScreen = 1
                        navController.navigate(ShowRick)},
                    icon = {
                        Icon(imageVector =
                        if (chosenScreen == 1)Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = { Text("Show Rick") }
                )

                NavigationBarItem(
                    selected = chosenScreen == 2,
                    onClick = {
                        chosenScreen = 2
                        navController.navigate(CreateRick)},
                    icon = {
                        Icon(imageVector =
                        if (chosenScreen == 2) Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = { Text("Create Rick") }
                )

                NavigationBarItem(
                    selected = chosenScreen == 3,
                    onClick = {
                        chosenScreen = 3
                        navController.navigate(CreateRick)},
                    icon = {
                        Icon(imageVector =
                        if (chosenScreen == 3)Icons.Filled.Home else Icons.Outlined.Home, contentDescription = null)
                    },
                    label = { Text("TODO") }
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = ShowRick
            ) {
                composable<ApiRick> {
                    ApiRickScreen(apiRickViewModel)
                }
                composable<ShowRick> {
                    ShowRickScreen(showRickViewModel = ShowRickViewModel())
                }
                composable<CreateRick> {
                    CreateRickScreen(createRickViewModel)
                }

            }
        }
    }
}