package com.example.rockpaperscissor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rockpaperscissor.ui.theme.RockPaperScissorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockPaperScissorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RockPaperScissorScreen()
                }
            }
        }
    }
}

@Composable
fun RockPaperScissorScreen() {

    var userChoice by remember {
        mutableStateOf(0)
    }

    var androidChoice by remember {
        mutableStateOf(0)
    }

    var userScore by remember {
        mutableStateOf(0)
    }

    var androidScore by remember {
        mutableStateOf(0)
    }

    var gameStart by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        ) {
        Image(
            painter = painterResource(id = R.drawable.appimage),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.score),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif
            )
            Text(
                text = "$userScore / $androidScore",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
        Row (
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
                ){

            if (gameStart) {
                // User choice Display
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.you_chose),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = stringResource(id = when(userChoice){
                            0 -> R.string.rock
                            1 -> R.string.paper
                            else -> R.string.scissor
                        }),
                        fontSize = 32.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Android Choice Display
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.android_chose),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = stringResource(id = when(androidChoice){
                            0 -> R.string.rock
                            1 -> R.string.paper
                            else -> R.string.scissor
                        }),
                        fontSize = 32.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Text(
                    text = stringResource(id = R.string.start),
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
                ){

            // Rock Button
            PressableButton(
                modifier = Modifier,
                label = R.string.rock,
                onTap = {
                    userChoice = 0
                    androidChoice = (0..2).random()
                    if (!gameStart){
                        gameStart = !gameStart
                    }
                    if(checkResult(userChoice = userChoice, androidChoice = androidChoice) == "user"){
                        userScore++
                    } else if (checkResult(userChoice = userChoice, androidChoice = androidChoice) == "android"){
                        androidScore++
                    }
                })

            // Paper Button
            PressableButton(
                modifier = Modifier,
                label = R.string.paper,
                onTap = {
                    userChoice = 1
                    androidChoice = (0..2).random()
                    if (!gameStart){
                        gameStart = !gameStart
                    }
                    if(checkResult(userChoice = userChoice, androidChoice = androidChoice) == "user"){
                        userScore++
                    } else if (checkResult(userChoice = userChoice, androidChoice = androidChoice) == "android"){
                        androidScore++
                    }
                })

            // Scissor Button
            PressableButton(
                modifier = Modifier,
                label = R.string.scissor,
                onTap = {
                    userChoice = 2
                    androidChoice = (0..2).random()
                    if (!gameStart){
                        gameStart = !gameStart
                    }
                    if(checkResult(userChoice = userChoice, androidChoice = androidChoice) == "user"){
                        userScore++
                    } else if (checkResult(userChoice = userChoice, androidChoice = androidChoice) == "android"){
                        androidScore++
                    }
                })
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.jetpack_compose_hashtag),
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(vertical = 15.dp)
        )
    }
}

private fun checkResult(userChoice: Int, androidChoice: Int): String? {
    when (userChoice) {
        0 -> {
            return when (androidChoice) {
                1 -> {
                    "android"
                }
                2 -> {
                    "user"
                }
                else -> {
                    null
                }
            }
        }
        1 -> {
            return when (androidChoice) {
                2 -> {
                    "android"
                }
                0 -> {
                    "user"
                }
                else -> {
                    null
                }
            }
        }
        else -> {
            return when (androidChoice) {
                0 -> {
                    "android"
                }
                1 -> {
                    "user"
                }
                else -> {
                    null
                }
            }
        }
    }
}

// Button
@Composable
fun PressableButton(modifier: Modifier = Modifier, @StringRes label: Int, onTap: () -> Unit){
    Button(
        onClick = onTap,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xffA020F0)
        ),
        modifier = modifier
            .size(width = 100.dp, height = 100.dp)

    ) {
        Surface(
            color = Color.Transparent
        ) {
            Text(
                text = stringResource(label),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RockPaperScissorTheme {
        RockPaperScissorScreen()
    }
}