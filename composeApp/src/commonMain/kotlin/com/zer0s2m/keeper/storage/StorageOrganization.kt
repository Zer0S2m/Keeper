package com.zer0s2m.keeper.storage

import com.zer0s2m.keeper.dto.Organization

/**
 * Basic storage for organizations.
 */
object StorageOrganization {

    /**
     * The currently selected organization.
     */
    private val currentOrganization: Organization? = null

    /**
     * All possible organizations.
     */
    private val organization: MutableList<Organization> = mutableListOf()

}
