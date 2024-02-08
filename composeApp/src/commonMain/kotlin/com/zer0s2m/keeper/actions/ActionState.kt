package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject

/**
 * Action repository - state.
 */
object ActionState : Action {

    /**
     * Set active organization from state.
     *
     * @param organizationID Organization ID.
     */
    internal fun setOrganization(organizationID: Long?) {
        StorageOrganization.getAllOrganizations().value.find { it.id == organizationID }
            ?.let { StorageOrganization.setCurrentOrganization(it) }
    }

    /**
     * Set active project from state.
     *
     * @param projectID Project ID.
     */
    internal fun setProject(projectID: Long?) {
        StorageProject.setCurrentProject(StorageProject.getProjects().value.find { it.id == projectID })
    }

}