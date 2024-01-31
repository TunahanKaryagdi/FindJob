package com.tunahankaryagdi.findjob.presentation.navigation


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun AppBottomNavBar(
     destinations : List<TopLevelDestination>,
     currentDestination: NavDestination?,
     onNavigateToDestination : (TopLevelDestination) -> Unit
) {

    val width = LocalConfiguration.current.screenWidthDp

    NavigationBar(
        modifier = Modifier
            .height((width * 0.14).dp),
        containerColor = CustomTheme.colors.secondaryBackground,
    ) {

        destinations.map { destination->
            val selected = currentDestination?.hierarchy?.any{ it.route == destination.route} == true
            NavigationBarItem(
                modifier = Modifier,
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    Icon(painter = painterResource(id = destination.painterId) , contentDescription = destination.name)
                },
                label = {
                    Text(
                        text = stringResource(id = destination.textId),
                        style = CustomTheme.typography.bodySmall
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CustomTheme.colors.primary,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = CustomTheme.colors.secondaryBackground
                ),
            )
        }
    }
}

