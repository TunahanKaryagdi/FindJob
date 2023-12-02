package com.tunahankaryagdi.findjob.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                items.forEachIndexed { index, drawerItem ->
                    NavigationDrawerItem(
                        label = {Text(drawerItem.title)},
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        content()
    }
}