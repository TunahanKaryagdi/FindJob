package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.profile.profileRoute
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.DrawerItemTitle
import kotlinx.coroutines.launch


data class DrawerItem(
    val icon: Painter,
    val id: DrawerItemTitle,
    val title: String,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onClickDrawerItem : (DrawerItem)->Unit,
    content: @Composable ()->Unit
) {

    val items = listOf(
        DrawerItem(icon = painterResource(id = R.drawable.ic_user), id = DrawerItemTitle.Profile, title = stringResource(
            id = R.string.profile
        )),
        DrawerItem(icon = painterResource(id = R.drawable.ic_applications), id = DrawerItemTitle.Applications, title = stringResource(
            id = R.string.applications
        )),
        DrawerItem(icon = painterResource(id = R.drawable.ic_job), id = DrawerItemTitle.Jobs, title = stringResource(
            id = R.string.jobs
        )),
        DrawerItem(icon = painterResource(id = R.drawable.ic_logout), id = DrawerItemTitle.Logout, title = stringResource(
            id = R.string.log_out
        )),
    )

    var selectedIndex by rememberSaveable{
        mutableStateOf(0)
    }
    val scope  = rememberCoroutineScope()
    val width = LocalConfiguration.current.screenWidthDp

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = CustomTheme.colors.secondaryBackground,
                modifier = Modifier.width((width * 0.6).dp)
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


                    items.forEachIndexed { index, drawerItem ->
                        NavigationDrawerItem(
                            label = {Text(drawerItem.title)},
                            selected = index == selectedIndex,
                            onClick = {
                                selectedIndex = index
                                onClickDrawerItem(drawerItem)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(painter = drawerItem.icon, contentDescription = drawerItem.title)
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