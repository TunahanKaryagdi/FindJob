package com.tunahankaryagdi.findjob.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomGoogleButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun SignupScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {

    val uiState by  viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    SignupScreen(
        modifier = modifier,
        navigateToHome = navigateToHome,
        onTrigger = viewModel::handleEvents,
        uiState = uiState,
        effect = effect
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navigateToHome : ()->Unit,
    onTrigger: (SignupEvent)->Unit,
    uiState: SignupUiState,
    effect: SignupEffect?
) {

    val snackbarState = remember{
        SnackbarHostState()
    }

    LaunchedEffect(key1 = effect){
        when (effect){
            is SignupEffect.ShowErrorMessage->{
                snackbarState.showSnackbar(message = effect.message, duration = SnackbarDuration.Short)

            }
            else->{

            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarState) },
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back) )
                },
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        SignupScreenContent(
            modifier = Modifier.padding(it),
            navigateToHome = navigateToHome,
            onTrigger = onTrigger,
            uiState = uiState,
            effect = effect
        )
    }

}


@Composable
fun SignupScreenContent(
    modifier: Modifier = Modifier,
    navigateToHome : ()->Unit,
    onTrigger: (SignupEvent)->Unit,
    uiState: SignupUiState,
    effect: SignupEffect?
) {



    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = stringResource(id = R.string.register_account),
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
            value = uiState.name,
            placeholder = stringResource(id = R.string.name) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Create, contentDescription = stringResource(id = R.string.name))
            },
            onValueChange = {
                onTrigger(SignupEvent.OnNameValueChange(it))
            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.surname,
            placeholder = stringResource(id = R.string.surname) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = stringResource(id = R.string.surname))
            },
            onValueChange = {
                onTrigger(SignupEvent.OnSurnameValueChange(it))
            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.email,
            placeholder = stringResource(id = R.string.email),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Email, contentDescription = stringResource(id = R.string.email))
            },
            onValueChange = {
                onTrigger(SignupEvent.OnEmailValueChange(it))
            }
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = uiState.password,
            placeholder = stringResource(id = R.string.password),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Lock, contentDescription = stringResource(id = R.string.password))
            },
            onValueChange = {
                onTrigger(SignupEvent.OnPasswordValueChange(it))
            }

        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                //onTrigger(SignupEvent.OnClickSignup)
                navigateToHome()
            },
            text = stringResource(id = R.string.sign_up),
        )

        SpacerHeight(size = CustomTheme.spaces.medium)

        Text(text = stringResource(id = R.string.or_continue_with))

        SpacerHeight(size = CustomTheme.spaces.small)


        CustomGoogleButton(
            onClick = {

            }
        )

        SpacerHeight(size = CustomTheme.spaces.small)

        //annotated string
        Row() {
            Text(text = stringResource(id = R.string.already_have_account))
            SpacerWidth(size = CustomTheme.spaces.extraSmall)
            Text(
                text = stringResource(id = R.string.login),
                style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold, color = CustomTheme.colors.secondaryText)
            )

        }
    }
}