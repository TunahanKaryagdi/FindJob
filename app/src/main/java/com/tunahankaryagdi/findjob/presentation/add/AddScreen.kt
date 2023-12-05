package com.tunahankaryagdi.findjob.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomToggleButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.JobTypes


@Composable
fun AddScreenRoute(
    modifier: Modifier = Modifier
) {
    AddScreen(modifier = modifier)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    modifier: Modifier = Modifier
){

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                title = {
                    Text(
                        text = stringResource(id = R.string.add_job),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        AddScreenContent(
            modifier = Modifier.padding(it)
        )
    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddScreenContent(
    modifier: Modifier = Modifier
) {


    val isChecked = remember{
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .padding(CustomTheme.spaces.medium)
    ) {

        Text(
            text = stringResource(id = R.string.job_type),
            style = CustomTheme.typography.bodyLarge
        )

        FlowRow() {
            enumValues<JobTypes>().forEach {
                CustomToggleButton(
                    text = it.name,
                    isSelected = isChecked.value,
                    onCheckedChange = {isChecked.value = it}
                )
                SpacerWidth(size = CustomTheme.spaces.small)
            }
        }

        SpacerHeight(size = CustomTheme.spaces.medium)
        Text(
            text = stringResource(id = R.string.title),
            style = CustomTheme.typography.bodyLarge
        )
        CustomOutlinedTextField(
            value = "",
            onValueChange = {}
        )
        
        SpacerHeight(size = CustomTheme.spaces.medium)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                Text(
                    text = stringResource(id = R.string.location),
                    style = CustomTheme.typography.bodyLarge
                )
                CustomOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = stringResource(id = R.string.location)
                        )
                    }
                )
            }

            SpacerWidth(size = CustomTheme.spaces.medium)

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.salary),
                    style = CustomTheme.typography.bodyLarge
                )
                CustomOutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Star, contentDescription = stringResource(
                        id = R.string.salary
                    ) )}
                )
            }
        }

        SpacerHeight(size = CustomTheme.spaces.medium)

        CustomButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ },
            text = stringResource(id = R.string.post) )


    }

}