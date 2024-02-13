package com.zer0s2m.keeper

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.graphs.CollectScreenHttpDashboard
import com.zer0s2m.keeper.navigation.graphs.CollectScreenOrganizationDashboard
import com.zer0s2m.keeper.navigation.runtime.rememberNavigationController
import com.zer0s2m.keeper.theme.AppTheme
import com.zer0s2m.keeper.utils.*
import com.zer0s2m.keeper.utils.loadStorageCollection
import com.zer0s2m.keeper.utils.loadStorageOrganization
import com.zer0s2m.keeper.utils.loadStorageProjects
import com.zer0s2m.keeper.utils.loadStorageState

@Composable
internal fun App() = AppTheme {
    val navigationController by rememberNavigationController(Screen.ORGANIZATION_DASHBOARD_SCREEN.name)
    val isLoadData = remember { mutableStateOf(false) }

    if (!isLoadData.value) {
        loadStorageOrganization()
        loadStorageProjects()
        loadStorageCollection()
        loadStorageState() // Load with the latest
    }

    isLoadData.value = true

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CollectScreenOrganizationDashboard(navigationController = navigationController)
        CollectScreenHttpDashboard(navigationController = navigationController)
    }
}

internal fun exit() {
    saveStorageOrganization()
    saveStorageProject()
    saveStorageState()
    saveStorageCollection()
}
