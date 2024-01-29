package com.tunahankaryagdi.findjob.presentation.home.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun RecentPostCard(
    modifier: Modifier = Modifier,
    job: Job,
) {
    Box(
        modifier = modifier
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

            CustomAsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                model = "${Constants.BASE_IMAGE_URL}${job.company.image}",
                type = ImageType.Company,
                contentScale = ContentScale.Inside
            )

            SpacerWidth(size = CustomTheme.spaces.small)
            Column(
                modifier = Modifier
                    .weight(4f)
            ) {
                Text(
                    text = job.title,
                    style = CustomTheme.typography.bodyLarge
                )
                Text(
                    text = job.type,
                    style = CustomTheme.typography.bodySmall
                )
            }
            SpacerWidth(size = CustomTheme.spaces.small)
            Text(
                modifier = Modifier
                    .weight(2f),
                text = "$${job.salary}/m",
                style = CustomTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}