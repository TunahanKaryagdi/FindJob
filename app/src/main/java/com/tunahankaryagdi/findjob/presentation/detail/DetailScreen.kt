package com.tunahankaryagdi.findjob.presentation.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.apply.ApplyScreenContent
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTinyButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun DetailScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateToApply : (String) -> Unit,
    navigatePop: () -> Unit
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        navigateToApply = navigateToApply,
        navigatePop = navigatePop,
        uiState = uiState
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navigateToApply: (String) -> Unit,
    navigatePop: () -> Unit,
    uiState: DetailUiState
) {


    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                 navigatePop()
                            },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        DetailScreenContent(
            modifier = modifier.padding(it),
            navigateToApply = navigateToApply,
            uiState = uiState
        )
    }



}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    navigateToApply: (String) -> Unit,
    uiState: DetailUiState
) {

    val context = LocalContext.current
    val width = LocalConfiguration.current.screenWidthDp


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

                    uiState.job?.let {job->

                        CustomAsyncImage(
                            modifier = Modifier
                                .size((width * 0.3).dp)
                                .align(CenterHorizontally)
                                .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                            model = "${Constants.BASE_IMAGE_URL}${uiState.job.company.image}", 
                            type = ImageType.Company
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
                                text = job.company.name,
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
                                text = stringResource(id = R.string.experience_of,uiState.job.qualifications[it].name,uiState.job.qualifications[it].experience),
                                style = CustomTheme.typography.labelLarge
                            )

                        }

                    }
                }
            }
        }


        uiState.job?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(CustomTheme.spaces.medium)
            ) {
                CustomButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { if (!uiState.isApplied) navigateToApply(it.id) },
                    text = if (uiState.isApplied) stringResource(id = R.string.applied) else stringResource(id = R.string.apply_now)
                )
                SpacerWidth(size = CustomTheme.spaces.medium)
                CustomTinyButton(
                    icon = Icons.Filled.Email,
                    onClick = {
                        gmailIntent(context,it.user.email,it.title)
                    }
                )
            }
        }

    }
}

fun openGmail(context: Context) {
    val packageName = "com.google.android.gm"

    val intent = Intent(Intent.ACTION_MAIN).apply {
        `package` = packageName
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL,"karyagditunahan@gmail.com")
        putExtra(Intent.EXTRA_SUBJECT,"hk")

    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {

        val playStoreIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("market://details?id=$packageName")
        }
        context.startActivity(playStoreIntent)
    }
}

fun gmailIntent(context: Context,email: String,title: String){
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, title)
    }
    if (intent.resolveActivity(context.packageManager) != null){
        context.startActivity(intent)
    }
}