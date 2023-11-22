package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun RecentPostCard(
    modifier: Modifier = Modifier,
    jobName : String,
    jobType : String,
    salary: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                CustomTheme.colors.secondaryBackground,
                RoundedCornerShape(CustomTheme.spaces.small)
            )
            .padding(CustomTheme.spaces.medium)

    ){

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier= Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = stringResource(id = R.string.job_image)
            )
            SpacerWidth(size = CustomTheme.spaces.small)
            Column(
                modifier = Modifier
                    .weight(4f)
            ) {
                Text(
                    text = jobName,
                    style = CustomTheme.typography.bodyLarge
                )
                Text(
                    text = jobType,
                    style = CustomTheme.typography.bodySmall
                )
            }
            SpacerWidth(size = CustomTheme.spaces.small)
            Text(
                modifier = Modifier
                    .weight(2f),
                text = "$$salary/m",
                style = CustomTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}