package com.tunahankaryagdi.findjob.presentation.recommended.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType

@Composable
fun RecommendedJobCard(
    modifier: Modifier = Modifier,
    job: Job
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.secondaryBackground, RoundedCornerShape(10.dp))
            .padding(CustomTheme.spaces.medium)

    ){
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                CustomAsyncImage(
                    modifier = Modifier
                        .weight(2f)
                        .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                    model = "${Constants.BASE_IMAGE_URL}/${job.company.image}",
                    type = ImageType.Company,
                    contentScale = ContentScale.Inside
                )

                SpacerWidth(size = CustomTheme.spaces.small)

                Column(
                    modifier = Modifier
                        .weight(7f)
                ) {
                    Text(
                        text = job.company.name,
                        style = CustomTheme.typography.bodySmall
                    )
                    Text(
                        text = job.title,
                        style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold, color = CustomTheme.colors.secondaryText)
                    )
                    Text(
                        text = job.location,
                        style = CustomTheme.typography.bodySmall
                    )
                }
            }
            SpacerHeight(size = CustomTheme.spaces.small)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Benzerlik Oranı")
                Text(text = "${job.salary}/Monthly")
            }
        }
    }
}