package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomTinyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon : ImageVector,

) {
    Box (
        modifier = Modifier
            .size(50.dp)
            .background(
                color = CustomTheme.colors.primary,
                shape = RoundedCornerShape(CustomTheme.spaces.small)
            )
            .clip(shape = RoundedCornerShape(CustomTheme.spaces.small))
            .padding(CustomTheme.spaces.extraSmall,)
            .clickable {
                onClick()
            }
    ){
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(CustomTheme.spaces.small)),
            imageVector = icon,
            tint = CustomTheme.colors.tertiaryText,
            contentDescription = ""
        )
    }
}