package com.tunahankaryagdi.findjob.presentation.login

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomCircularProgress
import com.tunahankaryagdi.findjob.presentation.components.CustomGoogleButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.presentation.signup.SignupEffect
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun LoginScreenRoute(
    modifier: Modifier = Modifier,
    viewModel : LoginViewModel = hiltViewModel(),
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect){
        if (effect == LoginEffect.NavigateToHome){
            navigateToHome()
        }

    }

    LoginScreen(
        modifier = modifier,
        navigateToSignup = navigateToSignup,
        uiState = uiState,
        effect = effect,
        onTrigger = viewModel::handleEvents
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToSignup: ()->Unit,
    uiState: LoginUiState,
    effect: LoginEffect?,
    onTrigger: (LoginEvent) -> Unit
) {

    val snackbarState = remember{ SnackbarHostState() }
    LaunchedEffect(key1 = effect){
        when (effect){
            is LoginEffect.ShowErrorMessage->{
                snackbarState.showSnackbar(message = effect.message, duration = SnackbarDuration.Short)
            }
            else->{

            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = { CustomTopAppbar() },
        containerColor = CustomTheme.colors.primaryBackground,
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
    ){
        LoginScreenContent(
            modifier = modifier.padding(it),
            navigateToSignup = navigateToSignup,
            uiState = uiState,
            effect = effect,
            onTrigger = onTrigger
        )
    }

}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navigateToSignup: ()->Unit,
    uiState: LoginUiState,
    effect: LoginEffect?,
    onTrigger: (LoginEvent)-> Unit
) {

    val context = LocalContext.current

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        onTrigger(LoginEvent.OnClickGoogleSignIn(result))
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = stringResource(id = R.string.welcome_back),
            style = CustomTheme.typography.titleLarge,
        )

        SpacerHeight(size = CustomTheme.spaces.small)

        Text(
            text = stringResource(id = R.string.fill_your_details),
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.email,
            placeholder = stringResource(id = R.string.email) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = stringResource(id = R.string.email))
            },
            onValueChange = {
                onTrigger(LoginEvent.OnEmailValueChange(it))
            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.password,
            placeholder = stringResource(id = R.string.password) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.password))
            },
            onValueChange = {
                onTrigger(LoginEvent.OnPasswordValueChange(it))
            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onTrigger(LoginEvent.OnClickLogin)
            },
            text = if (!uiState.isLoading) stringResource(id = R.string.login) else stringResource(
                id = R.string.loading
            ),
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        Text(text = stringResource(id = R.string.or_continue_with))

        SpacerHeight(size = CustomTheme.spaces.small)

        CustomGoogleButton(
            onClick =  {
                val googleSignInClient = getGoogleSignInClient(context)
                val signInIntent = googleSignInClient.signInIntent
                signInLauncher.launch(signInIntent)
            }
        )

        SpacerHeight(size = CustomTheme.spaces.small)

        //annotated string
        Row() {
            Text(text = stringResource(id = R.string.new_user))
            SpacerWidth(size = CustomTheme.spaces.extraSmall)
            Text(
                modifier = Modifier
                    .clickable {
                        navigateToSignup()
                    },
                text = stringResource(id = R.string.create_account),
                style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold, color = CustomTheme.colors.secondaryText)
            )
        }
    }
}

fun getGoogleSignInClient(context: Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("149429631601-56vf5u2b3t2v83sqpvb9i4m41nktlp3i.apps.googleusercontent.com")
        .requestId()
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context, gso)
}


