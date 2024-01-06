package com.tunahankaryagdi.findjob.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToEditProfile: () -> Unit
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect){
        if (effect == ProfileEffect.NavigateToEditProfile){
            navigateToEditProfile()
        }
    }

    ProfileScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onTrigger: (ProfileEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                title =  {Text(text = stringResource(id = R.string.profile))},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ProfileScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }
}


@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onTrigger: (ProfileEvent) -> Unit
){

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.profile),
                        modifier = Modifier.clip(CircleShape)
                    )

                    CustomOutlinedButton(
                        onClick = {
                            onTrigger(ProfileEvent.OnClickEditProfile)
                        },
                        text = stringResource(id = R.string.edit_profile))
                    
                }


                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.name),
                )
                Text(
                    text = uiState.name,
                    style = CustomTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.email)
                )
                Text(
                    text = uiState.email,
                    style = CustomTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.skills)
                )
            }
        }
        if(uiState.skills.isEmpty()){
            item {
                Text(text = "No skill yet")
            }
        }
        else{
            items(uiState.skills.size){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "• ${uiState.skills[it].name}",
                    style = CustomTheme.typography.labelLarge
                )
            }
        }

    }
}

