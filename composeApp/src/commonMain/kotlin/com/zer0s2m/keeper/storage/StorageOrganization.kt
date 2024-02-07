package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Organization

/**
 * Basic storage for organizations.
 */
object StorageOrganization : Storage {

    /**
     * The currently selected organization.
     */
    private val currentOrganization: MutableState<Organization?> = mutableStateOf(null)

    /**
     * All possible organizations.
     */
    private val organization: MutableState<MutableList<Organization>> = mutableStateOf(mutableListOf())

    /**
     * Get all available organizations.
     */
    internal fun getOrganization(): MutableState<MutableList<Organization>> {
        return organization
    }

    /**
     * Get the current active organization.
     *
     * @return An active organization.
     */
    internal fun getCurrentOrganization(): Organization? {
        return currentOrganization.value
    }

    /**
     * Add organizations.
     *
     * @param organization Organizations.
     * @return True if the list was changed as the result of the operation.
     */
    internal fun addAllOrganization(organization: Collection<Organization>): Boolean {
        return this.organization.value.addAll(organization)
    }

    /**
     * Set current organization as active.
     *
     * @param organization Organization.
     */
    internal fun setCurrentOrganization(organization: Organization) {
        currentOrganization.value = organization
    }

    /**
     * Perform initial setup of the entire storage.
     *
     * @param organization Organizations.
     */
    internal fun setup(organization: Collection<Organization>) {
        addAllOrganization(organization)
    }

}
