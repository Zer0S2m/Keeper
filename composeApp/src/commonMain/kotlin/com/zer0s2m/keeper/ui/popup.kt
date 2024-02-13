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
import com.zer0s2m.keeper.actions.ActionCollectionProject
import com.zer0s2m.keeper.actions.ActionOrganization
import com.zer0s2m.keeper.actions.ActionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
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
 * Modal window - creating or editing an organization.
 *
 * @param organization Initial organization.
 * @param stateModal Modal window states.
 * @param stateModalIsEdit A sign that the project will be edited.
 */
@Composable
internal fun ModalPopupCreateOrEditOrganization(
    organization: MutableState<Organization?>,
    stateModal: MutableState<Boolean>,
    stateModalIsEdit: MutableState<Boolean>
) {
    if (organization.value == null) {
        return
    }

    ModalPopup(
        stateModal = stateModal,
        modifierLayout = Modifier
            .width(BASE_WIDTH_POPUP)
            .height(BASE_HEIGHT_POPUP)
    ) {
        TitleModalPopup(text = if (!stateModalIsEdit.value)
            "Creation of an organization" else "Edition of an organization")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var text: String by remember { mutableStateOf(organization.value!!.title) }
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
                BaseButtonModal(
                    onClick = {
                        val validation: Validator = NotEmptyValidator()
                        if (!validation.validate(text)) {
                            label = validation.msg
                            hasError = true
                        } else {
                            if (!stateModalIsEdit.value) {
                                ActionOrganization.createOrganization(
                                    Organization(
                                        organization.value!!.id,
                                        text.trim()
                                    )
                                )
                            } else {
                                ActionOrganization.editOrganization(
                                    Organization(
                                        organization.value!!.id,
                                        text.trim()
                                    )
                                )
                            }

                            ActionOrganization.openModalCreateOrEditOrganizationPopup(
                                state = false,
                                isEdit = false,
                                organization = null
                            )
                        }
                    }
                ) {
                    Text("save")
                }
                BaseButtonModal(
                    onClick = { ActionOrganization.cancelCreateOrEditOrganization() },
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
 * Modal window - creating or editing a project.
 *
 * @param project Initial project.
 * @param stateModal Modal window states.
 * @param stateModalIsEdit A sign that the project will be edited.
 */
@Composable
internal fun ModalPopupCreateOrEditProject(
    project: MutableState<Project?>,
    stateModal: MutableState<Boolean>,
    stateModalIsEdit: MutableState<Boolean>
) {
    if (project.value == null) {
        return
    }

    ModalPopup(
        stateModal = stateModal,
        modifierLayout = Modifier
            .width(BASE_WIDTH_POPUP)
            .height(BASE_HEIGHT_POPUP)
    ) {
        TitleModalPopup(text = if (stateModalIsEdit.value) "Editing a project" else "Creating a project")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var text: String by remember { mutableStateOf(project.value!!.title) }
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
                BaseButtonModal(
                    onClick = {
                        val validation: Validator = NotEmptyValidator()
                        if (!validation.validate(text)) {
                            label = validation.msg
                            hasError = true
                        } else {
                            if (!stateModalIsEdit.value) {
                                ActionProject.createProject(
                                    Project(
                                        project.value!!.id,
                                        project.value!!.organizationID,
                                        text.trim()
                                    )
                                )
                            } else {
                                ActionProject.editProject(
                                    Project(
                                        project.value!!.id,
                                        project.value!!.organizationID,
                                        text.trim()
                                    )
                                )
                            }

                            ActionProject.openModalCreateOrEditProjectPopup(state = false, isEdit = false, null)
                        }
                    }
                ) {
                    Text("save")
                }
                BaseButtonModal(
                    onClick = { ActionProject.cancelCreateOrEditProject() },
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
 * Modal window - creating or editing a collection.
 *
 * @param collectionProject Initial collection.
 * @param stateModal Modal window states.
 * @param stateModalIsEdit A sign that the project will be edited.
 */
@Composable
internal fun ModalPopupCreateOrEditCollection(
    collectionProject: MutableState<CollectionProject?>,
    stateModal: MutableState<Boolean>,
    stateModalIsEdit: MutableState<Boolean>
) {
    if (collectionProject.value == null) {
        return
    }

    ModalPopup(
        stateModal = stateModal,
        modifierLayout = Modifier
            .width(BASE_WIDTH_POPUP)
            .height(BASE_HEIGHT_POPUP)
    ) {
        TitleModalPopup(text = if (stateModalIsEdit.value) "Editing a collection" else "Creating a collection")

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var text: String by remember { mutableStateOf(collectionProject.value!!.title) }
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
                BaseButtonModal(
                    onClick = {
                        val validation: Validator = NotEmptyValidator()
                        if (!validation.validate(text)) {
                            label = validation.msg
                            hasError = true
                        } else {
                            val newOrEditCollectionProject = CollectionProject(
                                collectionProject.value!!.id,
                                collectionProject.value!!.projectID,
                                text.trim()
                            )

                            if (stateModalIsEdit.value) {
                                ActionCollectionProject.editCollection(
                                    collectionProject = newOrEditCollectionProject
                                )
                            } else {
                                ActionCollectionProject.createCollectionProject(
                                    collectionProject = newOrEditCollectionProject
                                )
                            }

                            ActionCollectionProject.openModalCreateOrEditCollectionProjectPopup(
                                state = false,
                                isEdit = false,
                                collectionProject = null
                            )
                        }
                    }
                ) {
                    Text("save")
                }
                BaseButtonModal(
                    onClick = { ActionCollectionProject.cancelCreateOrEditProject() },
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

/**
 * Basic button for a modal window.
 *
 * @param onClick Will be called when the user clicks the button
 * @param colors [ButtonColors] that will be used to resolve the background
 * and content color for this button in different states.
 */
@Composable
private fun BaseButtonModal(
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit
) = Button(
    modifier = Modifier
        .width((BASE_WIDTH_POPUP - (PADDING * 4)) / 2 - 12.dp)
        .pointerHoverIcon(PointerIcon.Hand),
    onClick = onClick,
    colors = colors
) {
    content()
}
