package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.Constants.BASE_IMAGE_URL


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

            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                model = "${Constants.BASE_IMAGE_URL}${job.company.image}",
                contentDescription = stringResource(id = R.string.job_image),
                placeholder = painterResource(id = R.drawable.ic_default_company),
                error = painterResource(id = R.drawable.ic_default_company)
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