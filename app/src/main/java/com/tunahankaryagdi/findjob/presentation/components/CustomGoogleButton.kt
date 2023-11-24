package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomGoogleButton(
    modifier: Modifier = Modifier,
    size : Dp = 60.dp,
    onClick : ()-> Unit

) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = CustomTheme.colors.secondaryBackground,
                shape = RoundedCornerShape(CustomTheme.spaces.medium)
            )
            .clip(shape = RoundedCornerShape(CustomTheme.spaces.medium))
            .padding(10.dp)
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(id = R.string.login_with_google),
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(CustomTheme.spaces.medium))
        )
    }
}