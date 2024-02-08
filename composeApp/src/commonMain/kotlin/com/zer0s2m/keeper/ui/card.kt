package com.zer0s2m.keeper.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.actions.ActionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.utils.formatTitleOrganization

/**
 * Component - organization.
 *
 * Available actions:
 *
 * 1) When you click on a component, the organization will change
 *
 * @param organization Organization for drawing.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardItemOrganization(organization: Organization) {
    Card(
        modifier = Modifier
            .semantics { role = Role.Button }
            .fillMaxWidth()
            .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionOrganization.changeOrganization(organization) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(0.dp, PADDING * 2)
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
 *
 * @param project Organization for project.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardItemProject(project: Project) {
    Card(
        modifier = Modifier
            .semantics { role = Role.Button }
            .fillMaxWidth()
            .pointerHoverIcon(icon = PointerIcon.Hand),
        shape = RoundedCornerShape(SHAPE),
        interactionSource = remember { MutableInteractionSource() },
        onClick = { ActionProject.changeProject(project) }
    ) {
        Column(modifier = Modifier.padding(PADDING * 2, PADDING * 2)) {
            Text(text = project.title)
        }
    }
}
