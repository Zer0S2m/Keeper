package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.constant.WIDTH_RIGHT_PANEL
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.ui.*

/**
 * Screen to show available organizations.
 *
 * @param navigationController Controller for walking between screens.
 */
class OrganizationDashboard(override val navigationController: NavigationController) : BaseDashboard {

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        ModalPopupCreateOrganization(stateModal = StorageOrganization.expandedStateModalCreateOrganizationPopup)

        Column {
            Column(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                TopPanel()
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Row {
                RightPanel()
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                )

                if (StorageOrganization.getCurrentOrganization() != null) {
                    PanelProjects(navigationController = navigationController)
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                    )
                }
                if (StorageProject.getCurrentProject() != null) {
                    StorageProject.getCurrentProject()?.let {
                        PanelCollectionsProject(navigationController = navigationController)
                    }
                }
            }
        }
    }

}

/**
 * Left panel of the home page.
 *
 * Includes:
 *
 * 1) List of all available organizations.
 * 2) Button to add a new organization.
 */
@Composable
private fun RightPanel() {
    Column(
        modifier = Modifier
            .width(52.dp)
            .padding(PADDING)
    ) {
        ButtonAdd(
            modifier = Modifier.fillMaxWidth(),
            modifierButton = Modifier.height(40.dp),
            shape = RoundedCornerShape(SHAPE),
            onClick = { ActionOrganization.openModalCreateOrganizationPopup(true) }
        )
        RightOrganizationPanel(
            organizations = StorageOrganization.getAllOrganizations(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Panel - projects.
 *
 * Includes:
 *
 * 1) All available projects by the selected active organization.
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
private fun PanelProjects(navigationController: NavigationController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(WIDTH_RIGHT_PANEL)
    ) {
        StorageOrganization.getCurrentOrganization()?.let {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(PADDING * 2)
            ) {
                ProjectDashboard(navigationController = navigationController)
                    .setProjects(StorageProject.getAllProjectByOrganizationID(it.id))
                    .render()
            }
        }
    }
}

/**
 * Panel - collections.
 *
 * Includes:
 *
 * 1) todo
 *
 * @param navigationController Controller for walking between screens.
 */
@Composable
private fun PanelCollectionsProject(navigationController: NavigationController) {
    Column(modifier = Modifier.fillMaxSize()) {
        StorageProject.getCurrentProject()?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PADDING * 2)
            ) {
                CollectionProjectDashboard(navigationController = navigationController)
                    .setProjects(StorageCollectionProject
                        .getAllCollectionsProjectByProjectID(it.id))
                    .render()
            }
        }
    }
}
