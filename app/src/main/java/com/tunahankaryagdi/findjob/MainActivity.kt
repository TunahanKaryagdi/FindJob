package com.tunahankaryagdi.findjob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tunahankaryagdi.findjob.presentation.add.addRoute
import com.tunahankaryagdi.findjob.presentation.navigation.MainApp
import com.tunahankaryagdi.findjob.presentation.navigation.NavigationHost
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.ui.theme.FindJobTheme
import com.tunahankaryagdi.findjob.ui.theme.darkColors
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme(darkColors = darkColors()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CustomTheme.colors.primaryBackground,
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = CustomTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FindJobTheme {
        Greeting("Android")
    }
}