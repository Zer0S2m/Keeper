package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.screen.OrganizationDashboard
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import com.zer0s2m.keeper.ui.ModalPopupCreateOrganization

/**
 * Action repository - organizations.
 */
object ActionOrganization : Action {

    /**
     * Open a modal window to create an organization.
     *
     * @param state State of the modal window. `Active` or `inactive`
     */
    internal fun openModalCreateOrganizationPopup(state: Boolean) {
        StorageOrganization.setExpandedStateModalCreateOrganizationPopup(state)
    }

    /**
     * Creation of an organization. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrganization] to `inactive`.
     * 2) Adding a new organization to the repository [StorageOrganization].
     *
     * @param organization New organization.
     */
    internal fun createOrganization(organization: Organization) {
        openModalCreateOrganizationPopup(state = false)
        StorageOrganization.addOrganization(organization)
    }

    /**
     * Cancel the creation of an organization. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrganization] to `inactive`.
     */
    internal fun cancelCreateOrganization() {
        openModalCreateOrganizationPopup(state = false)
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
