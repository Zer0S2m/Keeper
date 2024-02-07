package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.screen.OrganizationDashboard
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState

/**
 * Action repository - organizations.
 */
object ActionOrganization : Action {

    internal fun createOrganization() {
        println(true)
    }

    /**
     * Set a new active organization and render the screen [OrganizationDashboard].
     *
     * @param organization New current active organization.
     */
    internal fun changeOrganization(organization: Organization) {
        StorageOrganization.setCurrentOrganization(organization = organization)

        StorageProject.setCurrentProject(project = null)

        StorageState.setCurrentOrganizationID(organizationID = organization.id)
        StorageState.setCurrentProjectID(projectID = null)
    }

}
