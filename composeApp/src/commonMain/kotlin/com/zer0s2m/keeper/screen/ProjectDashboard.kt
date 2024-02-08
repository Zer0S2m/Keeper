package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.actions.ActionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.ButtonAdd
import com.zer0s2m.keeper.ui.RightProjectsPanel

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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = PADDING * 3)
                .fillMaxWidth()
        ) {
            Text(
                text = "PROJECTS (${projects.value.size})",
                fontSize = 14.sp,
                lineHeight = 0.sp
            )
            ButtonAdd(
                modifier = Modifier
                    .height(20.dp)
                    .width(28.dp)
                    .padding(top = PADDING),
                modifierButton = Modifier
                    .height(20.dp)
                    .width(28.dp),
                shape = RoundedCornerShape(SHAPE),
                onClick = { ActionProject.createProject() }
            )
        }
        RightProjectsPanel(projects)
    }

    /**
     * Set available projects to the selected active organization.
     *
     * @param projects Projects.
     * @return Dashboard.
     */
    fun setProjects(projects: Collection<Project>): ProjectDashboard {
        this.projects.value.addAll(projects)
        return this
    }

}