package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object StorageState : Storage {

    private val currentOrganizationID: MutableState<Long?> = mutableStateOf(null)

    private val currentProjectID: MutableState<Long?> = mutableStateOf(null)

    internal fun setCurrentOrganizationID(organizationID: Long?) {
        currentOrganizationID.value = organizationID
    }

    internal fun setCurrentProjectID(projectID: Long?) {
        currentProjectID.value = projectID
    }

}