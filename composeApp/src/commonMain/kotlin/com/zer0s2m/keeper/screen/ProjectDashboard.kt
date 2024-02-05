package com.zer0s2m.keeper.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard

/**
 * Screen for displaying available projects in a specific organization.
 *
 * @param navigationController Controller for walking between screens.
 */
class ProjectDashboard(override val navigationController: NavigationController) : BaseDashboard {

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Text(text = "Projects")
    }

}