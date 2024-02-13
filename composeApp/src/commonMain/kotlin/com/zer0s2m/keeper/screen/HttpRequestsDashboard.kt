package com.zer0s2m.keeper.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard

/**
 * @param navigationController Controller for walking between screens.
 */
class HttpRequestsDashboard(override val navigationController: NavigationController) : BaseDashboard {

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Text(text = "HttpRequest")
    }

}
