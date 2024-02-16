package com.zer0s2m.keeper.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.HttpRequest
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
 * 2) Reverse screen navigation.
 *
 * @param navigationController
 */
@Composable
fun TopPanel(
    navigationController: NavigationController
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonArrowLeft(
            onClick = { navigationController.navigateBack() },
            shape = RoundedCornerShape(SHAPE),
            enabled = navigationController.isEnabledNavigateBack.value
        )
        ButtonGitHub(
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
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PADDING)
    ) {
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
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PADDING)
    ) {
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

/**
 * Panel.
 *
 * Includes:
 *
 * 1) A list of all available http requests in the identified active collection.
 *
 * @param httpRequests Available http requests.
 * @param modifier The modifier to apply to this layout.
 */
@Composable
fun RightHttpRequestsPanel(
    httpRequests: MutableState<MutableList<HttpRequest>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PADDING * 2)
    ) {
        items(
            items = httpRequests.value,
            key = { it.id }
        ) { httpRequest ->
            CardItemHttpRequest(httpRequest = httpRequest)
        }
    }
}

@Composable
fun TopPanelHttpRequest(httpRequest: HttpRequest) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = PADDING * 2)
            ) {
                Text(
                    text = httpRequest.method.name,
                    color = httpRequest.method.color,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(86.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(0.dp)
                        .width(PADDING * 2),
                )

                val urlHttpRequest: MutableState<String> = remember { mutableStateOf(httpRequest.url) }
                TextFieldURLHttpRequest(
                    url = urlHttpRequest.value,
                    onValueChange = { newValue: String ->
                        urlHttpRequest.value = newValue
                    }
                )
            }

            Button(
                onClick = { println(httpRequest) }
            ) {
                Text("send")
            }
        }
    }
}
