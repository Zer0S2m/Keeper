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
import com.zer0s2m.keeper.dto.Project
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
 *
 * @param organizations Available organizations.
 * @param modifier The modifier to apply to this layout.
 */
@Composable
fun RightOrganizationPanel(
    organizations: MutableState<MutableList<Organization>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = organizations.value,
            key = { it.id }
        ) { organization ->
            CardItemOrganization(
                organization = organization
            )
        }
    }
}

/**
 * Panel.
 *
 * Includes:
 *
 * 1) A list of all available projects in the identified active organization.
 *
 * @param projects Available projects.
 * @param modifier The modifier to apply to this layout.
 */
@Composable
fun RightProjectsPanel(
    projects: MutableState<MutableList<Project>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(projects.value) { project ->
            CardItemProject(project)
        }
    }
}