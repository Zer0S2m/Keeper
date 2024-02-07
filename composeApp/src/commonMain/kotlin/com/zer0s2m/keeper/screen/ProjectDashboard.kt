package com.zer0s2m.keeper.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard

/**
 * Screen for displaying available projects in a specific organization.
 *
 * @param navigationController Controller for walking between screens.
 */
class ProjectDashboard(override val navigationController: NavigationController) : BaseDashboard {

    private val projects: MutableState<MutableList<Project>> = mutableStateOf(mutableListOf())

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Text(
            text = "PROJECTS (${projects.value.size})",
            fontSize = 14.sp,
            lineHeight = 1.sp
        )
    }

    /**
     *
     */
    fun setProjects(projects: Collection<Project>): ProjectDashboard {
        this.projects.value.addAll(projects)
        return this
    }

}