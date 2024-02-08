package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.State

object StorageState : Storage {

    private val currentOrganizationID: MutableState<Long?> = mutableStateOf(null)

    private val currentProjectID: MutableState<Long?> = mutableStateOf(null)

    internal fun setCurrentOrganizationID(organizationID: Long?) {
        currentOrganizationID.value = organizationID
    }

    internal fun setCurrentProjectID(projectID: Long?) {
        currentProjectID.value = projectID
    }

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