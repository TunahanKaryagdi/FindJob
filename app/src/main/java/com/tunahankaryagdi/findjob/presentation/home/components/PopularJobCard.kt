package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun PopularJobCard(
    modifier: Modifier = Modifier,
    companyName : String,
    jobName : String,
    salary : Int,
    location : String
) {

    Box(
        modifier = modifier
            .background(
                CustomTheme.colors.secondaryBackground,
                RoundedCornerShape(CustomTheme.spaces.small)
            )
            .padding(CustomTheme.spaces.medium)

    ){

        Column() {
            Row() {
                Text(
                    text = companyName,
                    style = CustomTheme.typography.bodySmall
                )
            }
            Text(
                text = jobName,
                style = CustomTheme.typography.bodyLarge
            )
            Row {
                Text(
                    text = "$salary/m",
                    style = CustomTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                )
                SpacerWidth(size = CustomTheme.spaces.extraSmall)
                Text(
                    text = location,
                    style = CustomTheme.typography.bodySmall,
                )
            }
        }
    }
}