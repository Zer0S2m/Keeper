package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.screen.OrganizationDashboard
import com.zer0s2m.keeper.storage.StorageOrganization

/**
 * Action repository - organizations.
 */
object ActionOrganization : Action {

    fun createOrganization() {
        println(true)
    }

    /**
     * Set a new active organization and render the screen [OrganizationDashboard].
     *
     * @param organization New current active organization.
     */
    fun changeOrganization(organization: Organization) {
        StorageOrganization.setCurrentOrganization(organization)
    }

}