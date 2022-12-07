package com.example.lab4.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab4.ui.theme.Lab4Theme
import com.example.lab4.R
import com.example.lab4.ui.theme.BlueLightPrimary

@Composable
fun MainScreen(){
    /*
    * I referred to the code in the repository below while creating the Bottom Navigation
    * https://github.com/stevdza-san/NestedNavigationBottomBarDemo
    */
    val navController = rememberNavController()
    val context = LocalContext.current
    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.not_implemented),
                        Toast.LENGTH_LONG
                    ).show()
                },
                backgroundColor = BlueLightPrimary
            )
            { Icon(Icons.Filled.Add, contentDescription = "") }
        },
        bottomBar = { BottomBar(navController = navController) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp,1.dp,1.dp,50.dp)
            ) {
                BottomNavGraph(navController = navController)
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Students,
        BottomBarScreen.Professors,
        BottomBarScreen.Courses,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation() {
        screens.forEach { screen ->
            addScreen(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.addScreen(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label= {
               Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Nav Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = { navController.navigate(screen.route){
            launchSingleTop = true
        } })
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab4Theme {
        MainScreen()
    }
}
