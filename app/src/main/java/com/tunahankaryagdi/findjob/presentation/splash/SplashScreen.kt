package com.tunahankaryagdi.findjob.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R


@Composable
fun SplashScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToLogin: ()->Unit,
    navigateToHome: ()->Unit,
) {

    val uiState by  viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect){
        if (effect == SplashEffect.NavigateToHome){
            navigateToHome()
        }
        if (effect == SplashEffect.NavigateToLogin){
            navigateToLogin()
        }
    }

    SplashScreen(
        modifier = modifier,
        navigateToLogin = navigateToLogin,
        navigateToHome = navigateToHome
    )
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: ()->Unit,
    navigateToHome: ()->Unit,
) {


    SplashScreenContent(modifier = modifier)
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Icon(imageVector = Icons.Default.Person, contentDescription = stringResource(id = R.string.app_icon))
            CircularProgressIndicator()
        }
    }
}