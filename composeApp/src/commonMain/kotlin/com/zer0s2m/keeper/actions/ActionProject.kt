package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import com.zer0s2m.keeper.ui.ModalPopupCreateProject

/**
 * Action repository - projects.
 */
object ActionProject : Action {

    /**
     * Open a modal window to create a project.
     *
     * @param state State of the modal window. `Active` or `inactive`
     */
    internal fun openModalCreateProjectPopup(state: Boolean) {
        StorageProject.setExpandedStateModalCreateProjectPopup(value = state)
    }

    /**
     * Creating a new project. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateProject] to `inactive`.
     * 2) Adding a new project to the repository [StorageProject].
     *
     * @param
     */
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
        openModalCreateProjectPopup(false)
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

    /**
     * Removes the project from the system. Includes:
     *
     * 1) Deletes the project.
     * 2) Deletes collections associated with this project.
     * 3) Sets the current project to `null`.
     *
     * @param projectID Project ID.
     */
    internal fun deleteProject(projectID: Long) {
        StorageProject.removeProject(projectID = projectID)
        StorageCollectionProject.removeCollectionsProjectByProjectID(projectID = projectID)
        StorageProject.setCurrentProject(null)
    }

}