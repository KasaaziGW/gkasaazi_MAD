package com.example.lab4.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4.R
import com.example.lab4.model.*
import com.example.lab4.ui.theme.BlueLightDark
import com.example.lab4.ui.theme.Lab4Theme

@Composable
fun ViewProfessorsScreen() {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item {
            Text(text = stringResource(id = R.string.view) + " " + stringResource(id = R.string.professors), style = MaterialTheme.typography.h2)
        }
        items(professors){ professor ->
            addProfessor(professor)
        }
    }
}

@Composable
fun addProfessor(professor: Professor){
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
            Text(text = stringResource(id = R.string.name) + " " + professor.firstName + " " + professor.lastName, style = MaterialTheme.typography.h6)
            Text(text = stringResource(id = R.string.contact) + " " + professor.contact, style = MaterialTheme.typography.body1)
            Text(text = stringResource(id = R.string.status) + " " + professor.maritalStatus, style = MaterialTheme.typography.body1)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ViewProfessorsPreview() {
    Lab4Theme {
        ViewProfessorsScreen()
    }
}