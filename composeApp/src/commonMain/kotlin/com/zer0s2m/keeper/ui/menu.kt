package com.zer0s2m.keeper.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.enum.Icon

/**
 * Dropdown menu - for project.
 *
 * @param expanded Whether the menu is expanded or not.
 * @param modifier [Modifier] to be applied to the menu's content.
 * @param onClickDelete Action - delete a object.
 */
@Composable
fun MenuDropdownProject(
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onClickDelete: () -> Unit
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = modifier
    ) {
        DropdownMenuItemActionDelete(
            expanded = expanded,
            onClick = onClickDelete
        )
    }
}

/**
 * Dropdown menu - for organization.
 *
 * @param expanded Whether the menu is expanded or not.
 * @param modifier [Modifier] to be applied to the menu's content.
 * @param onClickDelete Action - delete a object.
 */
@Composable
fun MenuDropdownOrganization(
    expanded: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onClickDelete: () -> Unit
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = modifier
    ) {
        DropdownMenuItemActionDelete(
            expanded = expanded,
            onClick = onClickDelete
        )
    }
}

@Composable
private fun DropdownMenuItemActionDelete(
    expanded: MutableState<Boolean>,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
        onClick = {
            expanded.value = false
            onClick()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(end = PADDING * 3)) {
                Image(
                    painter = painterResource(resourcePath = Icon.ICON_DELETE_ROUNDED.path),
                    contentDescription = "Delete icon"
                )
            }
            Text("Delete")
        }
    }
}
