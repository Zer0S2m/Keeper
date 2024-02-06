package com.zer0s2m.keeper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.graphs.CollectScreenOrganizationDashboard
import com.zer0s2m.keeper.navigation.runtime.rememberNavigationController
import com.zer0s2m.keeper.theme.AppTheme
import com.zer0s2m.keeper.utils.loadStorageCollection
import com.zer0s2m.keeper.utils.loadStorageOrganization
import com.zer0s2m.keeper.utils.loadStorageProjects

@Composable
internal fun App() = AppTheme {
    val navigationController by rememberNavigationController(Screen.ORGANIZATION_DASHBOARD_SCREEN.name)

    loadStorageOrganization()
    loadStorageProjects()
    loadStorageCollection()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CollectScreenOrganizationDashboard(navigationController)
    }
}
