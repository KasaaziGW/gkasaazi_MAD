package com.example.to_doapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoListVM: ViewModel() {
    var todoList = mutableStateListOf<ToDoList>()

    fun addItem(newToDo: ToDoList){
        todoList.add(newToDo)
    }

    fun deleteItem(newToDo: ToDoList){
        todoList.remove(newToDo)
    }
}