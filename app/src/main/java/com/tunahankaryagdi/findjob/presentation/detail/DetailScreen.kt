package com.tunahankaryagdi.findjob.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.apply.ApplyScreenContent
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTinyButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun DetailScreenRoute(
    modifier: Modifier = Modifier,
    navigateToApply : () -> Unit
) {
    val job = Job(
        "id","Ui Designer","Spotify","Toronto","Full time","1500", listOf("ABC","BCA")
    )

    DetailScreen(
        modifier = modifier,
        job = job,
        navigateToApply = navigateToApply
    )
}

data class Job(
    val id : String = "",
    val title :String = "",
    val company :String = "",
    val location : String = "",
    val type : String = "",
    val salary : String = "",
    val qualifications : List<String> = emptyList()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigateToApply : () -> Unit,
    job: Job
) {


    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar()
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        DetailScreenContent(
            modifier = modifier.padding(it),
            navigateToApply = navigateToApply,
            job = job
        )
    }



}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    job: Job,
    navigateToApply: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CustomTheme.spaces.medium),
        ){
            item{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Image(
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .clip(RoundedCornerShape(10.dp)),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.company_image)
                    )

                    SpacerHeight(size = CustomTheme.spaces.medium)

                    Text(
                        modifier = Modifier.align(CenterHorizontally),
                        text = job.title,
                        style = CustomTheme.typography.titleNormal
                    )

                    SpacerHeight(size = CustomTheme.spaces.medium)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = job.company,
                            style = CustomTheme.typography.body.copy(
                                color = CustomTheme.colors.secondaryText,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        SpacerWidth(size = CustomTheme.spaces.small)
                        Text(
                            text = job.location,
                        )
                    }
                    SpacerHeight(size = CustomTheme.spaces.small)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(text = job.type)
                        Text(text = "$${job.salary}/m")
                    }

                    SpacerHeight(size = CustomTheme.spaces.medium)

                    Text(
                        text = stringResource(id = R.string.qualifications),
                        style = CustomTheme.typography.titleNormal
                    )

                    repeat(job.qualifications.size){
                        Text(
                            text = "• ${job.qualifications[it]}",
                            style = CustomTheme.typography.labelLarge
                        )

                    }
                }


            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(CustomTheme.spaces.medium)
        ) {
            CustomButton(
                modifier = Modifier
                    .weight(1f),
                onClick = { navigateToApply() },
                text = stringResource(id = R.string.apply_now)
            )
            SpacerWidth(size = CustomTheme.spaces.medium)
            CustomTinyButton(
                icon = Icons.Filled.Email,
                onClick = {}
            )
        }
    }
}