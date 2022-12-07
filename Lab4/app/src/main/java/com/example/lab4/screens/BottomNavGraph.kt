package com.example.lab4.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Students.route
    ) {
        composable(route = BottomBarScreen.Students.route){
            ViewStudentsScreen()
        }
        composable(route = BottomBarScreen.Professors.route){
            ViewProfessorsScreen()
        }
        composable(route = BottomBarScreen.Courses.route){
            ViewCoursesScreen()
        }
    }
}