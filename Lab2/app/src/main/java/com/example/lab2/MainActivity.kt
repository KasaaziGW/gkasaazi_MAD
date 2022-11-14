package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.lab2.ui.theme.Lab2Theme
import com.example.lab2.ui.theme.RedLightDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    // loading the image
    val initialImage = painterResource(id = R.drawable.scotty)
    val secondImage = painterResource(id = R.drawable.scotty_2)
    // declaring the state variables
    var input by remember { mutableStateOf("") }
    var inputFieldName by remember { mutableStateOf("") }
    var image by remember { mutableStateOf(initialImage) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        InputTextField(name = inputFieldName, changed = { inputFieldName = it })
        SayHello { input = inputFieldName; image = secondImage }
        ChangeImage(image = image)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(RedLightDark)
        ) {
            TextComposable(userInput = input)
        }
    }
}

@Composable
fun InputTextField(name: String, changed: (String) ->Unit){
    TextField(
        value = name,
        label = {Text(stringResource(id = R.string.enterName))},
        onValueChange = changed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun ChangeImage(image: Painter){
    Image(
        painter = image,
        contentDescription = stringResource(id = R.string.scotty),
        modifier = Modifier
            .padding(top = 40.dp, bottom = 40.dp)
            .size(190.dp)
            //.clip(CircleShape)
    )
}
@Composable
fun SayHello(clicked: () -> Unit){
    Button(onClick= clicked) {
        Text(
            stringResource(id = R.string.buttonHello),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun TextComposable(userInput: String){
    if(userInput.isNotEmpty()) {
        Text(
            stringResource(R.string.greeting) + " " + userInput,
            color = White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Theme {
        Greeting()
    }
}


