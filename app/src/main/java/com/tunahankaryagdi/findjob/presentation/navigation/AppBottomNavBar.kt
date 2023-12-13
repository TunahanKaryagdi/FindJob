package com.tunahankaryagdi.findjob.presentation.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


    NavigationBar(
        containerColor = CustomTheme.colors.secondaryBackground,
        modifier = Modifier,
        tonalElevation =  10.dp
    ) {

        destinations.map { destination->
            val selected = currentDestination?.hierarchy?.any{ it.route == destination.route} == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    Icon(imageVector = destination.icon , contentDescription = destination.name)
                },
                label = {
                    Text(text = stringResource(id = destination.textId))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CustomTheme.colors.primary,
                    unselectedIconColor = CustomTheme.colors.primaryBackground,
                    indicatorColor = CustomTheme.colors.secondaryBackground
                ),
                alwaysShowLabel = false
            )

        }

    }

}
