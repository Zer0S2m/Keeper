package com.zer0s2m.keeper.navigation.graphs

import androidx.compose.runtime.Composable
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.navigation.NavigationHost
import com.zer0s2m.keeper.navigation.foundation.composable
import com.zer0s2m.keeper.screen.CollectionProjectDashboard
import com.zer0s2m.keeper.screen.OrganizationDashboard
import com.zer0s2m.keeper.screen.ProjectDashboard

/**
 * Collect a screen for available projects in the selected organization.
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
fun CollectScreenProjectDashboard(navigationController: NavigationController) {
    NavigationHost(navigationController) {
        composable(Screen.PROJECT_DASHBOARD_SCREEN.name) {
            ProjectDashboard(
                navigationController = navigationController
            ).render()
        }
    }.build()
}

/**
 * Collect a screen for the available collections in this project.
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
fun CollectScreenCollectionProjectDashboard(navigationController: NavigationController) {
    NavigationHost(navigationController) {
        composable(Screen.COLLECTION_PROJECT_DASHBOARD_SCREEN.name) {
            CollectionProjectDashboard(
                navigationController = navigationController
            ).render()
        }
    }.build()
}

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
