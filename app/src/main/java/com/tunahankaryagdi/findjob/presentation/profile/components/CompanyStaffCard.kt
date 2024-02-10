package com.tunahankaryagdi.findjob.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.tunahankaryagdi.findjob.domain.model.company.CompanyStaff
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun CompanyStaffCard(
    modifier: Modifier = Modifier,
    companyStaff: CompanyStaff
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CustomTheme.spaces.small)
        ) {
            CustomAsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape),
                model = "${Constants.BASE_IMAGE_URL}${companyStaff.company.image}",
                type = ImageType.Company,
                contentScale = ContentScale.Inside
            )

            Column(
                modifier = Modifier
                    .weight(5f)
            ) {
                Text(
                    text = companyStaff.title,
                    style = CustomTheme.typography.labelLarge
                )
                Text(
                    text = companyStaff.company.name,
                    style = CustomTheme.typography.bodySmall
                )
            }
        }
    }
}