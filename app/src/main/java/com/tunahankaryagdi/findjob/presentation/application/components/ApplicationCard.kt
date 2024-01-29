package com.tunahankaryagdi.findjob.presentation.application.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


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

            CustomAsyncImage(
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape),
                model = "${Constants.BASE_IMAGE_URL}/${application.user.image}",
                type = ImageType.User,
                contentScale = ContentScale.Inside
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

        }

    }
}