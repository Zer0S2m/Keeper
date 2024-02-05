package com.zer0s2m.keeper

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.graphs.CollectScreenOrganizationDashboard
import com.zer0s2m.keeper.navigation.runtime.rememberNavigationController
import com.zer0s2m.keeper.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    val navigationController by rememberNavigationController(Screen.ORGANIZATION_DASHBOARD_SCREEN.name)

    Surface {
        CollectScreenOrganizationDashboard(navigationController)
    }
}

internal expect fun openUrl(url: String?)