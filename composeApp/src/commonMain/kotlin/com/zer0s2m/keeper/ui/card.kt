package com.zer0s2m.keeper.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.Organization
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
@OptIn(ExperimentalFoundationApi::class)
fun CardItemOrganization(organization: Organization) {
    Card(
        modifier = Modifier
            .semantics { role = Role.Button }
            .fillMaxWidth()
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .onClick { ActionOrganization.changeOrganization(organization) },
        shape = RoundedCornerShape(SHAPE),
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