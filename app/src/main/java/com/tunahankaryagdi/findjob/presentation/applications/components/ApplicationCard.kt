package com.tunahankaryagdi.findjob.presentation.applications.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplicationCard(
    modifier: Modifier = Modifier,
    application: Application
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
                Image(
                    modifier = Modifier
                        .weight(2f)
                        .clip(RoundedCornerShape(CustomTheme.spaces.small)),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.company_image)
                )

                SpacerWidth(size = CustomTheme.spaces.small)

                Column(
                    modifier = Modifier
                        .weight(7f)
                ) {
                    Text(
                        text = application.job.company.name,
                        style = CustomTheme.typography.bodySmall
                    )
                    Text(
                        text = application.job.title,
                        style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold, color = CustomTheme.colors.secondaryText)
                    )
                    Text(
                        text = application.job.location,
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
                Box(
                    modifier = Modifier
                        .background(color = if (application.status) Color.Yellow else Color.Red, shape = RoundedCornerShape(CustomTheme.spaces.small))
                        .padding(horizontal = CustomTheme.spaces.small, vertical = CustomTheme.spaces.extraSmall)
                ) {
                    Text(
                        text = if (application.status) stringResource(id = R.string.accepted) else stringResource(id = R.string.pending),
                        color = Color.White,
                        style = CustomTheme.typography.bodySmall
                    )
                }
                Text(text = "${application.job.salary}/Monthly")
            }
        }
    }
}