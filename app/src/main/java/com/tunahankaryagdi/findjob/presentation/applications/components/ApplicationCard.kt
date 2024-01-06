package com.tunahankaryagdi.findjob.presentation.applications.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplicationCard(
    modifier: Modifier = Modifier,
    application: Application
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.secondaryBackground)
    ){
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.company_image)
                )

                Column() {
                    Text(text = application.job.company.name)
                    Text(text = application.job.title)
                    Text(text = application.job.location)
                }
            }
            Row() {
                Box() {
                    Text(
                        text = if (application.status) stringResource(id = R.string.accepted) else stringResource(id = R.string.pending)
                    )
                }
            }
        }
    }
}