package com.zer0s2m.keeper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.theme.md_theme_light_error
import com.zer0s2m.keeper.validation.NotEmptyValidator
import com.zer0s2m.keeper.validation.Validator
import java.awt.event.KeyEvent

@Stable
private val BASE_WIDTH_POPUP: Dp = 360.dp

@Stable
private val BASE_HEIGHT_POPUP: Dp = 200.dp

/**
 * The base layout for the modal window. Extends a component [Popup].
 *
 * @param stateModal Modal window states.
 * @param modifierLayout Modifier to be applied to the layout corresponding to the [Surface].
 * @param modifierLayoutContent The modifier to be applied to the [Column].
 * @param content The inner content of the modal window.
 * @param onDismissRequest Executes when the user clicks outside the popup.
 */
@Composable
internal fun ModalPopup(
    stateModal: MutableState<Boolean>,
    modifierLayout: Modifier = Modifier
        .fillMaxSize(),
    modifierLayoutContent: Modifier = Modifier
        .fillMaxSize()
        .padding(PADDING * 4),
    onDismissRequest: () -> Unit = {},
    content: @Composable () -> Unit
) {
    if (stateModal.value) {
        Popup(popupPositionProvider = object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize
            ): IntOffset = IntOffset.Zero
        },
            onDismissRequest = {
                stateModal.value = false
                onDismissRequest()
            },
            properties = PopupProperties(focusable = true), onPreviewKeyEvent = { false }, onKeyEvent = {
                if (it.type == KeyEventType.KeyDown && it.awtEventOrNull?.keyCode == KeyEvent.VK_ESCAPE) {
                    stateModal.value = false
                    true
                } else {
                    false
                }
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.32f))
                    .pointerInput({ stateModal.value = false }) {
                        detectTapGestures(onPress = { stateModal.value = false })
                    },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    contentColor = contentColorFor(MaterialTheme.colors.surface),
                    modifier = modifierLayout
                        .shadow(24.dp, RoundedCornerShape(SHAPE))
                ) {
                    Column(
                        modifier = modifierLayoutContent
                            .pointerInput({}) {
                                detectTapGestures(onPress = {
                                    // Workaround to disable clicks on Surface background
                                    // https://github.com/JetBrains/compose-jb/issues/2581
                                })
                            },
                    ) {
                        content()
                    }
                }

            }
        }
    }
}

/**
 * Modal window - creating an organization.
 *
 * @param stateModal Modal window states.
 */
@Composable
internal fun ModalPopupCreateOrganization(
    stateModal: MutableState<Boolean>
) {
    ModalPopup(
        stateModal = stateModal,
        modifierLayout = Modifier
            .width(BASE_WIDTH_POPUP)
            .height(BASE_HEIGHT_POPUP)
    ) {
        TitleModalPopup(text = "Creation of an organization")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var text: String by remember { mutableStateOf("") }
            var hasError: Boolean by remember { mutableStateOf(false) }
            var label: String by remember { mutableStateOf("Title") }

            TextField(
                value = text,
                isError = hasError,
                label = {
                    Text(text = label)
                },
                onValueChange = { value ->
                    hasError = false
                    text = value
                    label = "Title"
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                minLines = 1
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .width((BASE_WIDTH_POPUP - (PADDING * 4)) / 2 - 12.dp)
                        .pointerHoverIcon(PointerIcon.Hand),
                    onClick = {
                        val validation: Validator = NotEmptyValidator()
                        if (!validation.validate(text)) {
                            label = validation.msg
                            hasError = true
                        } else {
                            ActionOrganization.openModalCreateOrganizationPopup(false)
                            ActionOrganization.createOrganization(Organization(
                                StorageOrganization.getLastID() + 1,
                                text.trim()
                            ))
                        }
                    }
                ) {
                    Text("Save")
                }
                Button(
                    modifier = Modifier
                        .width((BASE_WIDTH_POPUP - (PADDING * 4)) / 2 - 12.dp)
                        .pointerHoverIcon(PointerIcon.Hand),
                    onClick = { ActionOrganization.cancelCreateOrganization() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = md_theme_light_error,
                        contentColor = Color.White
                    )
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}

/**
 * Basic title for all modal windows.
 *
 * @param text The text to be displayed.
 */
@Composable
private fun TitleModalPopup(text: String) = Text(
    text = text,
    fontSize = 20.sp,
    modifier = Modifier
        .padding(bottom = 20.dp)
)
