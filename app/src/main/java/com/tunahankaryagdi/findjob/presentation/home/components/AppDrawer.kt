package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import kotlinx.coroutines.launch


data class DrawerItem(
    val icon: ImageVector,
    val title : String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    content: @Composable ()->Unit
) {

    val items = listOf(
        DrawerItem(icon = Icons.Filled.List, title = "adfd"),
        DrawerItem(icon = Icons.Filled.Search, title = "adfd"),
    )

    var selectedIndex by rememberSaveable{
        mutableStateOf(0)
    }
    val scope  = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = CustomTheme.colors.secondaryBackground,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            NavigationDrawerItemDefaults.ItemPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    SpacerHeight(size = CustomTheme.spaces.medium)

                    Image(
                        modifier = Modifier
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.job_image)
                    )

                    SpacerHeight(size = CustomTheme.spaces.small)

                    Text(
                        text = "Screen1",
                        style = CustomTheme.typography.titleLarge
                    )


                    Text(
                        text = "Screen2",
                        style = CustomTheme.typography.labelLarge
                    )

                    SpacerHeight(size = CustomTheme.spaces.large)

                    items.forEachIndexed { index, drawerItem ->
                        NavigationDrawerItem(
                            label = {Text(drawerItem.title)},
                            selected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(imageVector = drawerItem.icon, contentDescription = drawerItem.title)
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.Transparent,
                                unselectedContainerColor = Color.Transparent,
                            ),
                        )
                    }
                }
       }
        }
    ) {
        content()
    }
}