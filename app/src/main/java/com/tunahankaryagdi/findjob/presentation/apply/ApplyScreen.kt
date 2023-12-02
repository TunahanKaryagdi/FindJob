package com.tunahankaryagdi.findjob.presentation.apply

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplyScreenRoute(
    modifier: Modifier = Modifier
) {

    ApplyScreen(modifier = modifier)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "")
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.job_apply),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
                 },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ApplyScreenContent(
            modifier = Modifier.padding(it)
        )
    }


}

@Composable
fun ApplyScreenContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(CustomTheme.spaces.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.name).plus(" "+stringResource(id = R.string.surname)))
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                placeholder = stringResource(id = R.string.name).plus(" "+stringResource(id = R.string.surname))
            )

            SpacerHeight(size = CustomTheme.spaces.medium)

            Text(text = stringResource(id = R.string.email))
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "" ,
                onValueChange = {} ,
                placeholder = stringResource(id = R.string.email)
            )

            SpacerHeight(size = CustomTheme.spaces.medium)

            Text(text = stringResource(id = R.string.message))
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "",
                onValueChange = {},
                size = 100.dp,
                singleLine = false,
                placeholder = stringResource(id = R.string.message)
            )

            SpacerHeight(size = CustomTheme.spaces.medium)

            Text(text = stringResource(id = R.string.cv))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(
                        color = CustomTheme.colors.secondaryBackground,
                        shape = RoundedCornerShape(CustomTheme.spaces.extraSmall)
                    ),
                contentAlignment = Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.upload_here),
                        style = CustomTheme.typography.bodySmall.copy(CustomTheme.colors.secondaryText)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_upload), 
                        contentDescription = stringResource(id = R.string.upload_here))
                }
            }

        }
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.apply_now)
        )
    }
}