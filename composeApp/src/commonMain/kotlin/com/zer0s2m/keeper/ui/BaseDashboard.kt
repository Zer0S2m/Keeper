package com.zer0s2m.keeper.ui

import androidx.compose.runtime.Composable
import com.zer0s2m.keeper.navigation.NavigationController

/**
 * The main interface for building screens.
 *
 * Used in internal screen navigation.
 */
interface BaseDashboard {

    /**
     * Controller for walking between screens.
     */
    val navigationController: NavigationController

    /**
     * Call screen render.
     */
    @Composable
    fun render()

}
