package com.example.lab4.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Students: BottomBarScreen(
        route = "Students",
        title = "Students",
        icon = Icons.Default.Person
    )
    object Professors: BottomBarScreen(
        route = "Professors",
        title = "Professors",
        icon = Icons.Default.Face
    )
    object Courses: BottomBarScreen(
        route = "Courses",
        title = "Courses",
        icon = Icons.Default.List
    )
}
