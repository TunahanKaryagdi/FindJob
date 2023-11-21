package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun SpacerHeight(
    modifier: Modifier = Modifier,
    size: Dp
) {
    Spacer(modifier = modifier.height(size))
}

@Composable
fun SpacerWidth(
    modifier: Modifier = Modifier,
    size: Dp
) {
    Spacer(modifier = modifier.width(size))
}