package com.tunahankaryagdi.findjob.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier
) {
    ProfileScreen(modifier = modifier)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
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
        ProfileScreenContent(modifier = modifier.padding(it))
    }
}


@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier
){

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.profile),
                    modifier = Modifier.clip(CircleShape)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = stringResource(id = R.string.name),
                )
                CustomOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange ={}
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = stringResource(id = R.string.email)
                )
                CustomOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange ={}
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = stringResource(id = R.string.skills)
                )
            }
        }
        items(5){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "• MYfdkhfdajhfdasjfha",
                style = CustomTheme.typography.labelLarge
            )
        }

    }
}

