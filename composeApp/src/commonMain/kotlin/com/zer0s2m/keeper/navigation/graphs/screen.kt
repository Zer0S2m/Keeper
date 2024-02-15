package com.zer0s2m.keeper.navigation.graphs

import androidx.compose.runtime.Composable
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.navigation.NavigationHost
import com.zer0s2m.keeper.navigation.foundation.composable
import com.zer0s2m.keeper.screen.HttpDashboard
import com.zer0s2m.keeper.screen.OrganizationDashboard

/**
 * Collect screen for available organization.
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
fun CollectScreenOrganizationDashboard(navigationController: NavigationController) {
    NavigationHost(navigationController) {
        composable(Screen.ORGANIZATION_DASHBOARD_SCREEN.name) {
            OrganizationDashboard(
                navigationController = navigationController
            ).render()
        }
    }.build()
}

/**
 * Collect screen for available http.
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
fun CollectScreenHttpDashboard(navigationController: NavigationController) {
    NavigationHost(navigationController) {
        composable(Screen.HTTP_SCREEN.name) {
            HttpDashboard(
                navigationController = navigationController
            ).render()
        }
    }.build()
}
