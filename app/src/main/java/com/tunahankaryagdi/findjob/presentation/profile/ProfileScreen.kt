package com.tunahankaryagdi.findjob.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType
import org.jetbrains.annotations.Async


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

    val width = LocalConfiguration.current.screenWidthDp

    uiState.userDetail?.let {
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {


                        CustomAsyncImage(
                            modifier = Modifier
                                .size((width * 0.25).dp)
                                .clip(CircleShape),
                            model = "${Constants.BASE_IMAGE_URL}${uiState.userDetail.image}",
                            type = ImageType.User)


                        CustomOutlinedButton(
                            onClick = {
                                onTrigger(ProfileEvent.OnClickEditProfile)
                            },
                            text = stringResource(id = R.string.edit_profile))
                    }


                    SpacerHeight(size = CustomTheme.spaces.medium)

                    Text(
                        text = stringResource(id = R.string.name),
                        style = CustomTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.userDetail.nameSurname,
                        style = CustomTheme.typography.body
                    )


                    SpacerHeight(size = CustomTheme.spaces.small)

                    Divider(
                        color = CustomTheme.colors.primary
                    )

                    SpacerHeight(size = CustomTheme.spaces.small)

                    Text(
                        text = stringResource(id = R.string.email),
                        style = CustomTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.userDetail.email,
                        style = CustomTheme.typography.body
                    )

                    SpacerHeight(size = CustomTheme.spaces.small)
                    Divider(
                        color = CustomTheme.colors.primary
                    )
                    SpacerHeight(size = CustomTheme.spaces.small)

                    Text(
                        text = stringResource(id = R.string.skills),
                        style = CustomTheme.typography.bodyLarge
                    )
                }
            }
            if(uiState.userDetail.skills.isEmpty()){
                item {
                    Text(text = stringResource(id = R.string.no_skills))
                }
            }
            else{
                items(uiState.userDetail.skills.size){
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "• ${uiState.userDetail.skills[it].experience} year experience in ${uiState.userDetail.skills[it].name}",
                        style = CustomTheme.typography.labelLarge
                    )

                }
            }

        }
    }
}

