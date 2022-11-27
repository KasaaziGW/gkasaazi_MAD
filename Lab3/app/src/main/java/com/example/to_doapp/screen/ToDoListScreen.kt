@file:OptIn(ExperimentalFoundationApi::class)

package com.example.to_doapp.screen

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_doapp.R
import com.example.to_doapp.model.ToDoList
import com.example.to_doapp.model.ToDoListVM
import com.example.to_doapp.ui.theme.*
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToDoAppScreen() {
    val viewModel: ToDoListVM = viewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = {
        FloatingActionButton(
        onClick = {showDialog = true}
        )
        { Icon(Icons.Filled.Add, contentDescription = "") }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        if (showDialog) {
            addItemDialog(
                context,
                dismissDialog = { showDialog = false },
                { viewModel.addItem(it) })
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(BlueLightVariant)
            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    color = White,
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 5.dp,
                    horizontal = 4.dp
                )
            )
            {
                items(viewModel.todoList, key = {item -> item.id }) { item ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart ||
                                it == DismissValue.DismissedToEnd) {
                                // delete the item from database
                                viewModel.deleteItem(item)
                                Toast.makeText(
                                    context,
                                    context.resources.getString(R.string.taskRemoved),
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@rememberDismissState true
                            }
                            return@rememberDismissState false
                        })
                    ToDoApp(listItem = item, context, dismissState)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ToDoApp(listItem: ToDoList, context: Context, dismissState: DismissState) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> LightGray
                    DismissValue.DismissedToEnd -> Red
                    DismissValue.DismissedToStart -> Red
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val iconTint = when (dismissState.targetValue) {
                DismissValue.Default -> Red
                DismissValue.DismissedToEnd -> White
                DismissValue.DismissedToStart -> White
            }
            val iconScale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.8f else 1f
            )
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = color)
                    .padding(horizontal = 16.dp),
                contentAlignment = alignment,
            ) {
                Icon(
                    modifier = Modifier.scale(iconScale),
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(id = R.string.screen_home_swipe_delete_task),
                    tint = iconTint,
                )
            }
        }) {
        Card(
            elevation = 2.dp,
            shape = RoundedCornerShape(5.dp),
            backgroundColor = BlueLightDark,
            contentColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(2.dp, color = MaterialTheme.colors.primary),
            modifier = Modifier
                .padding(8.dp)
                .height(40.dp)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                val isChecked = remember { mutableStateOf(false) }
                var decoration by remember { mutableStateOf(TextDecoration.None) }
                if (!isChecked.value) decoration = TextDecoration.None
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = { isChecked.value = it; decoration = TextDecoration.LineThrough },
                    modifier = Modifier
                        .size(5.dp)
                        .padding(8.dp, 12.dp, 0.dp, 0.dp),
                    enabled = true,
                    colors = CheckboxDefaults.colors(
                        checkedColor = BlueLightPrimary,
                        uncheckedColor = MaterialTheme.colors.onPrimary,
                        checkmarkColor = MaterialTheme.colors.onPrimary
                    )
                )
                Text(
                    text = listItem.task,
                    modifier = Modifier
                        .padding(17.dp,4.dp,0.dp,0.dp),
                    style = MaterialTheme.typography.body1,
                    textDecoration = decoration
                )
            }
        }
    }
}

@Composable
fun addItemDialog(context: Context, dismissDialog:() -> Unit, addToDo: (ToDoList) -> Unit){
    var taskTextField by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={Text(text = stringResource(id = R.string.addItem), style = MaterialTheme.typography.h6)},
        text = {
            Column(modifier = Modifier.padding(top=20.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                TextField(label = {Text(text=stringResource(id = R.string.task), style = MaterialTheme.typography.body1)}, value = taskTextField, onValueChange = {taskTextField=it})
                Spacer(modifier = Modifier.height(10.dp))
            }
        },
        confirmButton = {
            Button(onClick = {
                if(taskTextField.isNotEmpty()) {
                    val newID = UUID.randomUUID().toString();
                    addToDo(ToDoList(newID, taskTextField))
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.taskAdded),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.addTask), style = MaterialTheme.typography.button)
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.cancel), style = MaterialTheme.typography.button)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoAppTheme(darkTheme = false) {
        ToDoAppScreen()
    }
}