package com.tunahankaryagdi.findjob.presentation.application.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.application.Application
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
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
                    .weight(9f)
            ) {
                Text(
                    text = application.user.nameSurname,
                    style = CustomTheme.typography.body.copy(fontWeight = FontWeight.Bold, color = CustomTheme.colors.secondaryText)
                )
                Text(
                    text = application.user.email,
                    style = CustomTheme.typography.bodySmall
                )

            }
            
            Icon(imageVector = Icons.Default.Close, contentDescription = stringResource(id = R.string.close))
            SpacerWidth(size = CustomTheme.spaces.small)
            Icon(imageVector = Icons.Default.Done, contentDescription = stringResource(id = R.string.done))

        }

    }
}