package com.zer0s2m.keeper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.actions.ActionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.constant.WIDTH_ACTIVE_CARD
import com.zer0s2m.keeper.constant.WIDTH_RIGHT_PANEL
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.theme.md_theme_light_primary
import com.zer0s2m.keeper.utils.formatTitleOrganization

/**
 * Component - organization.
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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun CardItemOrganization(organization: Organization) {
    val expandedDropdownMenu: MutableState<Boolean> = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
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
            .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionOrganization.changeOrganization(organization) }
    ) {
        MenuDropdownOrganization(
            expanded = expandedDropdownMenu,
            onClickDelete = { ActionOrganization.deleteOrganization(organizationID = organization.id) },
            onClickEdit = {
                ActionOrganization.openModalCreateOrEditOrganizationPopup(
                    state = true,
                    isEdit = true,
                    organization = organization
                )
            }
        )

        Row(modifier = Modifier.fillMaxSize()) {
            var offsetX: Dp = 0.dp
            if (organization == StorageOrganization.getCurrentOrganization()) {
                Box(
                    modifier = Modifier
                        .height(38.dp)
                        .width(WIDTH_ACTIVE_CARD)
                        .background(md_theme_light_primary)
                )
                offsetX = (-1).dp
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, PADDING * 2)
            ) {
                Text(
                    text = formatTitleOrganization(organization.title),
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.sp,
                    modifier = Modifier.offset(x = offsetX)
                )
            }
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
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun CardItemProject(project: Project) {
    val expandedDropdownMenu: MutableState<Boolean> = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
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
            .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionProject.changeProject(project) }
    ) {
        MenuDropdownProject(
            modifier = Modifier.width(WIDTH_RIGHT_PANEL - (PADDING * 4)),
            expanded = expandedDropdownMenu,
            onClickDelete = { ActionProject.deleteProject(projectID = project.id) },
            onClickEdit = {
                ActionProject.openModalCreateOrEditProjectPopup(
                    state = true,
                    isEdit = true,
                    project = Project(
                        project.id,
                        project.organizationID,
                        project.title
                    )
                )
            }
        )

        Row(modifier = Modifier.fillMaxSize()) {
            var deletePadding: Dp = 0.dp
            if (project == StorageProject.getCurrentProject()) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(WIDTH_ACTIVE_CARD)
                        .background(md_theme_light_primary)
                )
                deletePadding = WIDTH_ACTIVE_CARD
            }

            Column(
                modifier = Modifier.padding(
                    start = (PADDING * 2) - deletePadding,
                    top = PADDING * 2,
                    end = PADDING * 2,
                    bottom = PADDING * 2
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = project.title)
                    ButtonMenuRounded(
                        onClick = { expandedDropdownMenu.value = true },
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp),
                        shape = RoundedCornerShape(SHAPE)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardItemCollectionProject(collectionProject: CollectionProject) {
    OutlinedCard(
        modifier = Modifier
            .semantics { role = Role.Button }
            .fillMaxWidth()
            .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        onClick = { println(collectionProject) }
    ) {
        Column(modifier = Modifier.padding(PADDING * 2, PADDING * 2)) {
            Text(text = collectionProject.title)
        }
    }
}