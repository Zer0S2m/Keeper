package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditOrganization

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

    object StorageOrganizationStateModal : Storage {

        /**
         * Current state of the modal [ModalPopupCreateOrEditOrganization] when creating the organization.
         */
        internal val expandedStateModalCreateOrganizationPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * State of the modal window, either creating a project or editing it.
         */
        internal val isEditStateModalCreateOrEditOrganizationPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * The initial object for editing and creating an organization.
         */
        internal val initialOrganizationStateModal: MutableState<Organization?> = mutableStateOf(null)

        /**
         * Set the state of the modal window - creating an organization.
         *
         * @param value `Active` or `inactive`.
         */
        internal fun setExpandedStateModalCreateOrganizationPopup(value: Boolean) {
            expandedStateModalCreateOrganizationPopup.value = value
        }

        /**
         * Set the state of the modal window, either creating an organization or editing it.
         *
         * @param value `Create` or `Edit`.
         */
        internal fun setIsEditStateModalCreateOrEditOrganizationPopup(value: Boolean) {
            isEditStateModalCreateOrEditOrganizationPopup.value = value
        }

        /**
         * Set up initial organization.
         *
         * @param organization Initial organization.
         */
        internal fun setInitialOrganizationStateModal(organization: Organization?) {
            initialOrganizationStateModal.value = organization
        }

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
     * @param organization Organization.
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
     * @param organizations Organizations.
     * @return True if the list was changed as the result of the operation.
     */
    internal fun addAllOrganization(organizations: Collection<Organization>): Boolean {
        return this.organizations.value.addAll(organizations)
    }

    /**
     * Get all available organizations.
     *
     * @return Organizations.
     */
    internal fun getAllOrganizations(): MutableState<MutableList<Organization>> {
        return organizations
    }

    /**
     * Set current organization as active.
     *
     * @param organization Organization.
     */
    internal fun setCurrentOrganization(organization: Organization?) {
        currentOrganization.value = organization
    }

    /**
     * Install the organization into the repository.
     *
     * @param organization Organization.
     */
    internal fun setOrganization(organization: Organization) {
        val newOrganizations: MutableList<Organization> = this.organizations.value.toMutableList()
        var indexSetting: Int = -1

        newOrganizations.forEachIndexed { index, newOrganization ->
            if (newOrganization.id == organization.id) {
                indexSetting = index
            }
        }

        if (indexSetting != -1) {
            newOrganizations[indexSetting] = organization
        }

        this.organizations.value = newOrganizations
    }

    /**
     * Perform initial setup of the entire storage.
     *
     * @param organization Organizations.
     */
    fun setup(organization: Collection<Organization>) {
        addAllOrganization(organization)
    }

    /**
     * Get the organization's latest unique identifier.
     *
     * @return Latest unique identifier.
     */
    internal fun getLastID(): Long {
        return getLastID(models = organizations.value)
    }

    /**
     * Deletes a organization by unique identifier.
     *
     * @param organizationID Unique identification.
     */
    internal fun removeOrganization(organizationID: Long) {
        organizations.value = organizations.value.filter { organization: Organization ->
            organization.id != organizationID
        }.toMutableList()
    }

}
