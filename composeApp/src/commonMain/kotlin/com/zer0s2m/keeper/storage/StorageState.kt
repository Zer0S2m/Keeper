package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.State

/**
 * General condition of all screens.
 */
object StorageState : Storage {

    /**
     * The currently selected organization.
     */
    private val currentOrganizationID: MutableState<Long?> = mutableStateOf(null)

    /**
     * Current selected project organization.
     */
    private val currentProjectID: MutableState<Long?> = mutableStateOf(null)

    /**
     * Set current organization.
     *
     * @param organizationID `ID`.
     */
    internal fun setCurrentOrganizationID(organizationID: Long?) {
        currentOrganizationID.value = organizationID
    }

    /**
     * Set current project.
     *
     * @param projectID `ID`.
     */
    internal fun setCurrentProjectID(projectID: Long?) {
        currentProjectID.value = projectID
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "StorageState[CurrentOrganizationID=${currentOrganizationID.value} " +
                "CurrentProjectID=${currentProjectID.value}]"
    }

    /**
     * Get state as class date.
     */
    internal fun getStateDTO(): State {
        return State(
            organizationID = currentOrganizationID.value,
            projectID = currentProjectID.value
        )
    }

}