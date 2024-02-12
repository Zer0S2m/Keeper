package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.screen.OrganizationDashboard
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditOrganization

/**
 * Action repository - organizations.
 */
object ActionOrganization : Action {

    /**
     * Open a modal window to create an organization.
     *
     * @param state State of the modal window. `Active` or `inactive`
     * @param isEdit `Create` or `Edit`.
     * @param organization Initial organization.
     */
    internal fun openModalCreateOrEditOrganizationPopup(state: Boolean, isEdit: Boolean, organization: Organization?) {
        StorageOrganization.StorageOrganizationStateModal
            .setExpandedStateModalCreateOrganizationPopup(value = state)
        StorageOrganization.StorageOrganizationStateModal
            .setIsEditStateModalCreateOrEditOrganizationPopup(value = isEdit)
        StorageOrganization.StorageOrganizationStateModal
            .setInitialOrganizationStateModal(organization = organization)
    }

    /**
     * Creation of an organization. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditOrganization] to `inactive`.
     * 2) Adding a new organization to the repository [StorageOrganization].
     * 3) Sets the original organization
     * [StorageOrganization.StorageOrganizationStateModal.initialOrganizationStateModal] to `null`.
     *
     * @param organization New organization.
     */
    internal fun createOrganization(organization: Organization) {
        openModalCreateOrEditOrganizationPopup(state = false, isEdit = false, organization = null)
        StorageOrganization.addOrganization(organization)
    }

    /**
     * Editing of an organization. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditOrganization] to `inactive`.
     * 2) Setting an edited organization to the repository [StorageOrganization].
     * 3) Sets the original organization
     * [StorageOrganization.StorageOrganizationStateModal.initialOrganizationStateModal] to `null`.
     *
     * @param organization New organization.
     */
    internal fun editOrganization(organization: Organization) {
        openModalCreateOrEditOrganizationPopup(state = false, isEdit = false, organization = null)
        StorageOrganization.setOrganization(organization)
    }

    /**
     * Cancel the creation of an organization. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditOrganization] to `inactive`.
     */
    internal fun cancelCreateOrEditOrganization() {
        openModalCreateOrEditOrganizationPopup(state = false, isEdit = false, organization = null)
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

    /**
     * Deleting an organization. Includes:
     *
     * 1) Delete an organization
     * 2) Deleting all projects included in the organization
     * 3) Deleting all collections included in remote projects
     * 4) Sets the active organization to null if it matches the deleted organization
     *
     * @param organizationID
     */
    internal fun deleteOrganization(organizationID: Long) {
        StorageOrganization.getCurrentOrganization()?.let {
            if (it.id == organizationID) {
                StorageOrganization.setCurrentOrganization(organization = null)
            }
        }

        StorageProject.getAllProjectByOrganizationID(organizationID = organizationID).forEach {
            ActionProject.deleteProject(projectID = it.id)
        }

        StorageOrganization.removeOrganization(organizationID = organizationID)
    }

}
