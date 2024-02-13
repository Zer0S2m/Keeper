package com.zer0s2m.keeper.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.navigation.NavigationController
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
                openUrl(url = "https://github.com/Zer0S2m")
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
        items(
            items = projects.value,
            key = { it.id }
        ) { project ->
            CardItemProject(project = project)
        }
    }
}

/**
 * Panel.
 *
 * Includes:
 *
 * 1) A list of all available collections in the identified active project
 *
 * @param collectionsProject Available collections.
 * @param navigationController Controller for walking between screens.
 */
@Composable
fun RightCollectionProjectPanel(
    collectionsProject: MutableState<MutableList<CollectionProject>>,
    navigationController: NavigationController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp),
        verticalArrangement = Arrangement.spacedBy(PADDING * 2),
        horizontalArrangement = Arrangement.spacedBy(PADDING * 2),
    ) {
        items(
            items = collectionsProject.value,
            key = { it.id }
        ) { collectionProject ->
            CardItemCollectionProject(
                collectionProject = collectionProject,
                navigationController = navigationController
            )
        }
    }
}
