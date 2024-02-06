package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.TopPanel

/**
 * Screen to show available organizations.
 *
 * @param navigationController Controller for walking between screens.
 */
class OrganizationDashboard(override val navigationController: NavigationController) : BaseDashboard {

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Column {
            Column (
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                TopPanel()
            }
            Divider (
                color = Color.Gray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Column (modifier = Modifier.fillMaxSize()) {
                Text(text = "Organizations")
            }
        }
    }

}