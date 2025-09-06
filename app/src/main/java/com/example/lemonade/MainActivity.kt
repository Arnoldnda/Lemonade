package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

// fonction de representatin d'une étape
@Composable
fun LemonadeStep(
    ressourceImage: Painter,
    ressourceDescriptiion: String,
    RessourceText: String,
    onClickImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = onClickImage,
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFFDFFFD6),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(24.dp)
            ) {
            Image(
                painter = ressourceImage,
                contentDescription = ressourceDescriptiion
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = RessourceText,
            fontSize = 18.sp,
            modifier = Modifier
        )
    }

}

// réunion de toute les étapes
@Composable
fun AllStepsForLemonade(modifier: Modifier = Modifier) {
    Column (modifier = modifier) {
        var steps by remember { mutableStateOf(1) }

        when(steps) {
            1 -> {
                LemonadeStep(
                    ressourceImage = painterResource(R.drawable.lemon_tree),
                    ressourceDescriptiion = stringResource(R.string.content_description_one),
                    RessourceText = stringResource(R.string.etape_one_text),
                    onClickImage = {steps++}
                )
            }

            2 -> {
                var random by remember(steps) { mutableStateOf((2..4).random()) }
                var click by remember { mutableStateOf(0) }

                LemonadeStep(
                    ressourceImage = painterResource(R.drawable.lemon_squeeze),
                    ressourceDescriptiion = stringResource(R.string.content_description_two),
                    RessourceText = stringResource(R.string.etape_two_text),
                    onClickImage = {
                        if (click == random) {
                            steps++
                        }
                        click++
                    }
                )

            }

            3 -> {
                LemonadeStep(
                    ressourceImage = painterResource(R.drawable.lemon_drink),
                    ressourceDescriptiion = stringResource(R.string.content_description_three),
                    RessourceText = stringResource(R.string.etape_three_text),
                    onClickImage = {steps++}
                )
            }

            4 -> {
                LemonadeStep(
                    ressourceImage = painterResource(R.drawable.lemon_restart),
                    ressourceDescriptiion = stringResource(R.string.content_description_four),
                    RessourceText = stringResource(R.string.etape_four_text),
                    onClickImage = {steps++}
                )
            }

            else -> steps = 1
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFEB3B), // Jaune vif
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        AllStepsForLemonade(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // important pour éviter que le contenu passe sous la barre
                .wrapContentSize(Alignment.Center)
        )
    }
}