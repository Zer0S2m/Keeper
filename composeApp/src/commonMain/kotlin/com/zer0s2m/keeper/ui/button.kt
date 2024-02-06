package com.zer0s2m.keeper.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.zer0s2m.keeper.constant.PADDING

/**
 * Button - github.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable.
 * @param interactionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for this Button. You can create and pass in your own remembered
 * [MutableInteractionSource] if you want to observe [Interaction]s and customize the
 * appearance / behavior of this Button in different [Interaction]s.
 * @param shape Defines the surface's shape as well its shadow.
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ButtonGitHub(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Surface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled,
        shape = shape,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier
                .pointerHoverIcon(icon = PointerIcon.Hand)
                .padding(PADDING),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(resourcePath = com.zer0s2m.keeper.enum.Icon.ICON_GITHUB.path),
                contentDescription = "GitHub icon"
            )
        }
    }
}

/**
 * Button component - creating a new organization.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the layout corresponding to the [Surface].
 * @param modifierButton Modifier to be applied to the button.
 * @param shape Defines the surface's shape as well its shadow.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ButtonAddOrganization(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modifierButton: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
) {
    Surface(
        onClick = onClick,
        shape = shape,
        modifier = modifier.semantics { role = Role.Button },
        enabled = enabled
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifierButton.pointerHoverIcon(icon = PointerIcon.Hand),
            content = {
                Image(
                    painter = painterResource(resourcePath = com.zer0s2m.keeper.enum.Icon.ICON_ADD.path),
                    contentDescription = "Add icon"
                )
            }
        )
    }
}
