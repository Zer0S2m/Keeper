package com.zer0s2m.keeper.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.actions.ActionCollectionProject
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.actions.ActionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.constant.WIDTH_RIGHT_PANEL
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.enum.Screen
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.utils.formatTitleOrganization

/**
 * Standalone component - organization.
 *
 * Available actions:
 *
 * 1) When you click on a component, the organization will change.
 * 2) Deleting an organization via drop-down men.
 * 3) Editing an organization via a drop-down menu
 *
 * Contains:
 *
 * 1) Drop-down menu for organization management
 *
 * @param organization Organization for drawing.
 */
@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun CardItemOrganization(organization: Organization) {
    val expandedDropdownMenu: MutableState<Boolean> = remember { mutableStateOf(false) }
    val enabled: MutableState<Boolean> = mutableStateOf(true)

    var modifier: Modifier = Modifier
    if (organization == StorageOrganization.getCurrentOrganization()) {
        enabled.value = false
        modifier = modifier.borderActiveCard()
    }

    Surface(
        modifier =
            Modifier
                .semantics { role = Role.Button }
                .fillMaxSize()
                .onPointerEvent(
                    eventType = PointerEventType.Press,
                    pass = PointerEventPass.Final,
                ) { event ->
                    if (event.button?.isSecondary == true) {
                        expandedDropdownMenu.value = true
                    }
                }
                .pointerHoverIcon(icon = PointerIcon.Hand)
                .then(modifier),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionOrganization.changeOrganization(organization) },
        enabled = enabled.value
    ) {
        MenuDropdownOrganization(
            expanded = expandedDropdownMenu,
            onClickDelete = { ActionOrganization.deleteOrganization(organizationID = organization.id) },
            onClickEdit = {
                ActionOrganization.openModalCreateOrEditOrganizationPopup(
                    state = true,
                    isEdit = true,
                    organization = organization,
                )
            },
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, PADDING * 3),
        ) {
            Text(
                text = formatTitleOrganization(organization.title),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                lineHeight = 1.sp
            )
        }
    }
}

/**
 * Component - project.
 *
 * Available actions:
 *
 * 1) When you click on a component, the project will change
 * 2) Deleting a project via drop-down menu.
 * 3) Editing a project via a drop-down menu.
 *
 * Contains:
 *
 * 1) Drop-down menu for project management
 *
 * @param project Organization for project.
 */
@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun CardItemProject(project: Project) {
    val expandedDropdownMenu: MutableState<Boolean> = remember { mutableStateOf(false) }

    var modifier: Modifier = Modifier
    if (project == StorageProject.getCurrentProject()) {
        modifier = modifier.borderActiveCard()
    }

    Surface(
        modifier =
            Modifier
                .semantics { role = Role.Button }
                .fillMaxWidth()
                .onPointerEvent(
                    eventType = PointerEventType.Press,
                    pass = PointerEventPass.Final,
                ) { event ->
                    if (event.button?.isSecondary == true) {
                        expandedDropdownMenu.value = true
                    }
                }
                .pointerHoverIcon(icon = PointerIcon.Hand)
                .then(modifier),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionProject.changeProject(project) },
    ) {
        MenuDropdownProject(
            modifier = Modifier.width(WIDTH_RIGHT_PANEL - (PADDING * 4)),
            expanded = expandedDropdownMenu,
            onClickDelete = { ActionProject.deleteProject(projectID = project.id) },
            onClickEdit = {
                ActionProject.openModalCreateOrEditProjectPopup(
                    state = true,
                    isEdit = true,
                    project =
                        Project(
                            project.id,
                            project.organizationID,
                            project.title,
                        ),
                )
            },
        )

        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier =
                    Modifier.padding(PADDING * 2, PADDING * 3),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = project.title)
                    ButtonMenuRounded(
                        onClick = { expandedDropdownMenu.value = true },
                        modifier =
                            Modifier
                                .width(20.dp)
                                .height(20.dp),
                        shape = RoundedCornerShape(SHAPE),
                        widthImage = 16.dp,
                        heightImage = 16.dp,
                    )
                }
            }
        }
    }
}

/**
 * Component - collection.
 *
 * Available actions:
 *
 * 1) Deleting a collection via drop-down menu.
 * 2) Editing a collection via a drop-down menu.
 *
 * Contains:
 *
 * 1) Drop-down menu for collection management
 *
 * @param collectionProject Collection.
 * @param navigationController Controller for walking between screens.
 */
@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun CardItemCollectionProject(
    collectionProject: CollectionProject,
    navigationController: NavigationController,
) {
    val interactionSourceHover: MutableInteractionSource = remember { MutableInteractionSource() }
    val isHover: MutableState<Boolean> = remember { mutableStateOf(false) }
    val expandedDropdownMenu: MutableState<Boolean> = remember { mutableStateOf(false) }
    val animatedCardColor: State<Color> = setAnimateColorAsStateInCard(isHover = isHover)

    setHoverInComponent(
        interactionSource = interactionSourceHover,
        isHover = isHover
    )

    Surface(
        modifier =
            Modifier
                .semantics { role = Role.Button }
                .fillMaxWidth()
                .height(120.dp)
                .onPointerEvent(
                    eventType = PointerEventType.Press,
                    pass = PointerEventPass.Final,
                ) { event ->
                    if (event.button?.isSecondary == true) {
                        expandedDropdownMenu.value = true
                    }
                }
                .pointerHoverIcon(icon = PointerIcon.Hand)
                .hoverable(interactionSource = interactionSourceHover),
        shape = RoundedCornerShape(SHAPE),
        onClick = {
            StorageCollectionProject.setCurrentCollectionProject(
                collectionProject = collectionProject,
            )
            navigationController.navigate(Screen.HTTP_SCREEN.name)
        },
        border = BorderStroke(1.dp, animatedCardColor.value)
    ) {
        Column(modifier = Modifier.padding(PADDING * 2, PADDING * 2)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Document",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 1.sp,
                    )
                    ButtonMenuRounded(
                        onClick = { expandedDropdownMenu.value = true },
                        modifier =
                            Modifier
                                .width(20.dp)
                                .height(20.dp),
                        shape = RoundedCornerShape(SHAPE),
                        widthImage = 16.dp,
                        heightImage = 16.dp,
                    )
                }
                MenuDropdownCollection(
                    expanded = expandedDropdownMenu,
                    modifier = Modifier.width(180.dp),
                    onClickEdit = {
                        ActionCollectionProject.openModalCreateOrEditCollectionProjectPopup(
                            state = true,
                            isEdit = true,
                            collectionProject =
                                CollectionProject(
                                    id = collectionProject.id,
                                    title = collectionProject.title,
                                    projectID = collectionProject.projectID,
                                ),
                        )
                    },
                    onClickDelete = {
                        ActionCollectionProject.deleteCollectionProject(
                            collectionProjectID = collectionProject.id,
                        )
                    },
                )
            }
            Spacer(modifier = Modifier.height(PADDING))
            Text(
                text = collectionProject.title,
                fontSize = 14.sp,
                lineHeight = 1.sp,
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardItemHttpRequest(httpRequest: HttpRequest) {
    Card(
        modifier =
            Modifier
                .semantics { role = Role.Button }
                .fillMaxWidth()
                .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { println(httpRequest) },
    ) {
        Column(modifier = Modifier.padding(PADDING * 2, PADDING * 3)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = httpRequest.method.name,
                    color = httpRequest.method.color,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(86.dp),
                    fontSize = 14.sp,
                )
                Text(httpRequest.title)
            }
        }
    }
}

@Composable
private fun Modifier.borderActiveCard() = this
    .then(Modifier.border(1.dp, MaterialTheme.colors.primary, RoundedCornerShape(SHAPE)))

@Composable
private fun setHoverInComponent(
    interactionSource: MutableInteractionSource,
    isHover: MutableState<Boolean>
) {
    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> {
                    interactions.add(interaction)
                    isHover.value = true
                }
                is HoverInteraction.Exit -> {
                    interactions.add(interaction)
                    isHover.value = false
                }
            }
        }
    }
}

@Composable
private fun setAnimateColorAsStateInCard(
    isHover: MutableState<Boolean>,
    borderColor: Color = MaterialTheme.colors.primary,
    borderHover: Color = MaterialTheme.colors.onBackground
): State<Color> {
    return animateColorAsState(
        targetValue = if (isHover.value) borderColor else borderHover,
        animationSpec = tween(0, 0, LinearEasing)
    )
}