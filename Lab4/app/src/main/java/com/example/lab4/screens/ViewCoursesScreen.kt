package com.example.lab4.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4.model.Course
import com.example.lab4.model.Professor
import com.example.lab4.model.courses
import com.example.lab4.model.professors
import com.example.lab4.ui.theme.BlueLightDark
import com.example.lab4.ui.theme.Lab4Theme

@Composable
fun ViewCoursesScreen() {
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 4.dp,
                horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {
                Text(text = stringResource(id = com.example.lab4.R.string.view) + " " + stringResource(id = com.example.lab4.R.string.courses), style = MaterialTheme.typography.h2)
            }
            items(courses){ course ->
                addCourse(course)
            }
        }
}

@Composable
fun addCourse(course: Course){
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = BlueLightDark,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(text = stringResource(id = com.example.lab4.R.string.courseName) + " " + course.courseName, style = MaterialTheme.typography.h6)
            Text(text = stringResource(id = com.example.lab4.R.string.units) + " " + course.units, style = MaterialTheme.typography.body1)
            Text(text = stringResource(id = com.example.lab4.R.string.semester) + " " + course.semesterOffered, style = MaterialTheme.typography.body1)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ViewCoursesPreview() {
    Lab4Theme {
        ViewCoursesScreen()
    }
}
