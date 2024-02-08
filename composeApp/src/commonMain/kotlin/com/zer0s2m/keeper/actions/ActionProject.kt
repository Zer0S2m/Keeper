package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import com.zer0s2m.keeper.ui.ModalPopupCreateProject

/**
 * Action repository - projects.
 */
object ActionProject : Action {

    internal fun openModalCreateProjectPopup(state: Boolean) {
        StorageProject.setExpandedStateModalCreateProjectPopup(value = state)
    }

    internal fun createProject(project: Project) {
        openModalCreateProjectPopup(false)
        StorageProject.addOrganization(project = project)
    }

    /**
     * Cancel the creation the project. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateProject] to `inactive`.
     */
    internal fun cancelCreateProject() {
        StorageProject.setExpandedStateModalCreateProjectPopup(value = false)
    }

    /**
     * Set up a new active project.
     *
     * @param project Active project.
     */
    internal fun changeProject(project: Project) {
        StorageProject.setCurrentProject(project = project)

        StorageState.setCurrentProjectID(projectID = project.id)
    }

}