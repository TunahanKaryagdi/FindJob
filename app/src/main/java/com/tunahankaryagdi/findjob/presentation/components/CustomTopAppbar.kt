package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppbar(
    modifier: Modifier = Modifier,
    title  : @Composable ()-> Unit = {},
    navigationIcon : @Composable () ->Unit = {}
) {


    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(horizontal = CustomTheme.spaces.medium),
        title = title,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = CustomTheme.colors.secondaryText,
            titleContentColor = CustomTheme.colors.secondaryText
        ),
        navigationIcon = navigationIcon
    )
}