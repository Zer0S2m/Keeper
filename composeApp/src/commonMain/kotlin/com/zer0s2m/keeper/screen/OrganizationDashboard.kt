package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.ButtonAddOrganization
import com.zer0s2m.keeper.ui.RightOrganizationPanel
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
            Column(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                TopPanel()
            }
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Row {
                RightPanel()
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    StorageOrganization.getCurrentOrganization()?.let {
                        Text(text = it.title)
                    }
                }
            }
        }
    }

}

/**
 * Left panel of the home page.
 *
 * Includes:
 *
 * 1) List of all available organizations.
 * 2) Button to add a new organization.
 */
@Composable
private fun RightPanel() {
    Column(modifier = Modifier
        .width(52.dp)
        .padding(PADDING)
    ) {
        RightOrganizationPanel(
            organizations = StorageOrganization.getOrganization(),
            modifier = Modifier.fillMaxWidth()
        )
        ButtonAddOrganization(
            modifier = Modifier.fillMaxWidth(),
            modifierButton = Modifier.height(40.dp),
            shape = RoundedCornerShape(SHAPE),
            onClick = { ActionOrganization.createOrganization() }
        )
    }
}
