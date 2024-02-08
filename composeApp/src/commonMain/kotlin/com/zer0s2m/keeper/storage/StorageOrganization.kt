package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.ui.ModalPopupCreateOrganization

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
    private val organizations: MutableState<MutableList<Organization>> = mutableStateOf(mutableListOf())

    /**
     * Current state of the modal [ModalPopupCreateOrganization] when creating the organization.
     */
    internal val expandedStateModalCreateOrganizationPopup: MutableState<Boolean> = mutableStateOf(false)

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
     * @param organization Organization.
     * @return True if the list was changed as the result of the operation.
     */
    internal fun addOrganization(organization: Organization) {
        val newOrganizations: MutableList<Organization> = mutableListOf()
        newOrganizations.addAll(organizations.value)
        newOrganizations.add(organization)

        this.organizations.value = newOrganizations
    }

    /**
     * Add organizations.
     *
     * @param organization Organizations.
     * @return True if the list was changed as the result of the operation.
     */
    internal fun addAllOrganization(organization: Collection<Organization>): Boolean {
        return organizations.value.addAll(organization)
    }

    internal fun getAllOrganizations(): MutableState<MutableList<Organization>> {
        return organizations
    }

    /**
     * Set current organization as active.
     *
     * @param organization Organization.
     */
    internal fun setCurrentOrganization(organization: Organization) {
        currentOrganization.value = organization
    }

    internal fun setExpandedStateModalCreateOrganizationPopup(value: Boolean) {
        expandedStateModalCreateOrganizationPopup.value = value
    }

    /**
     * Perform initial setup of the entire storage.
     *
     * @param organization Organizations.
     */
    internal fun setup(organization: Collection<Organization>) {
        addAllOrganization(organization)
    }

    /**
     * Get the organization's latest unique identifier.
     *
     * @return Latest unique identifier.
     */
    internal fun getLastID(): Long {
        if (organizations.value.isEmpty()) {
            return 0
        }

        var lastID: Long = 0

        organizations.value.forEach {
            if (it.id > lastID) {
                lastID = it.id
            }
        }

        return lastID
    }

}
