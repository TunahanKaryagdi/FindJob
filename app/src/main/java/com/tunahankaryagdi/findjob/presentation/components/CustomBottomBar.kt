package com.tunahankaryagdi.findjob.presentation.components


import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tunahankaryagdi.findjob.presentation.navigation.TopLevelDestination
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomBottomBar(
    destinations : List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination : (TopLevelDestination) -> Unit
) {

    val height = LocalConfiguration.current.screenHeightDp

    BottomAppBar(
        modifier = Modifier
            .height((height * 0.08).dp),
        containerColor = CustomTheme.colors.secondaryBackground,
    ) {

        destinations.map {destination->
            val selected = currentDestination?.hierarchy?.any{it.route == destination.route} ?: true
            BottomNavigationItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.painterId),
                        contentDescription =  stringResource(id = destination.textId),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.textId),
                        style = CustomTheme.typography.labelSmall
                    )
                },
            )
        }
    }

}

