package com.tunahankaryagdi.findjob.presentation.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomGoogleButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import kotlinx.coroutines.launch



@Composable
fun LoginScreenRoute(
    modifier: Modifier = Modifier,
    viewModel : LoginViewModel = hiltViewModel(),
    navigateToSignup: () -> Unit
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LoginScreen(
        modifier = modifier,
        navigateToSignup = navigateToSignup,
        uiState = uiState,
        effect = effect
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToSignup: ()->Unit,
    uiState: LoginUiState,
    effect: LoginEffect?
) {
    var text by remember {
        mutableStateOf("")
    }
    val signInRequestCode = 1



    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
                    println("succesfull login ${gsa.displayName}")
                } else {
                    println("gsa not null but error")
                }
            } catch (e: ApiException) {
                println("gsa null")
            }
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
            value = text,
            placeholder = stringResource(id = R.string.email) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = stringResource(id = R.string.email))
            },
            onValueChange = {

            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            placeholder = stringResource(id = R.string.password) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.password))
            },
            onValueChange = {

            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {},
            text = stringResource(id = R.string.login),
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        Text(text = stringResource(id = R.string.or_continue_with))


            CustomGoogleButton {
                authResultLauncher.launch(signInRequestCode)
            }


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
                style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold)
            )

        }
    }
}