package com.zer0s2m.keeper.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.utils.openUrl

/**
 * Top panel.
 *
 * Includes:
 *
 * 1) GitHub link.
 */
@Composable
fun TopPanel() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonGitHub(
            modifier = Modifier.padding(start = 2.dp),
            shape = RoundedCornerShape(SHAPE),
            onClick = {
                openUrl("https://github.com/Zer0S2m")
            }
        )
    }
}

/**
 * Left panel.
 *
 * Includes:
 *
 * 1) Available organizations.
 */
@Composable
fun RightOrganizationPanel(
    organizations: MutableState<MutableList<Organization>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(organizations.value) { organization ->
            CardItemOrganization(
                organization = organization
            )
        }
    }
}
