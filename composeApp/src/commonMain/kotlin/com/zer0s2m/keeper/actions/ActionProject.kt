package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditProject

/**
 * Action repository - projects.
 */
object ActionProject : Action {

    /**
     * Open a modal window to create or edit a project.
     *
     * @param state State of the modal window. `Active` or `inactive`.
     * @param isEdit `Create` or `Edit`.
     * @param project Initial project.
     */
    internal fun openModalCreateOrEditProjectPopup(state: Boolean, isEdit: Boolean, project: Project?) {
        StorageProject.StorageProjectStateModal.setExpandedStateModalCreateOrEditProjectPopup(value = state)
        StorageProject.StorageProjectStateModal.setIsEditStateModalCreateOrEditProjectPopup(value = isEdit)
        StorageProject.StorageProjectStateModal.setInitialProjectStateModal(project = project)
    }

    /**
     * Creating a new project. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditProject] to `inactive`.
     * 2) Adding a new project to the repository [StorageProject].
     * 3) Sets the original project [StorageProject.StorageProjectStateModal.initialProjectStateModal] to `null`.
     *
     * @param project Project to add to repository.
     */
    internal fun createProject(project: Project) {
        openModalCreateOrEditProjectPopup(state = false, isEdit = false, project = null)
        StorageProject.addProject(project = project)
    }

    /**
     * Editing a project. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditProject] to `inactive`.
     * 2) Settings an edited project to the repository [StorageProject].
     * 3) Sets the original project [StorageProject.StorageProjectStateModal.initialProjectStateModal] to `null`
     *
     * @param project Replacement project in the repository.
     */
    internal fun editProject(project: Project) {
        openModalCreateOrEditProjectPopup(state = false, isEdit = false, project = null)
        StorageProject.setProject(project = project)
    }

    /**
     * Cancel the creation the project. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditProject] to `inactive`.
     */
    internal fun cancelCreateOrEditProject() {
        openModalCreateOrEditProjectPopup(state = false, isEdit = false, project = null)
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