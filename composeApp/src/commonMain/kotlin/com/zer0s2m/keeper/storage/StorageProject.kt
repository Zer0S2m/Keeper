package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditProject

/**
 * Basic storage for projects.
 */
object StorageProject : Storage {

    /**
     * Current selected project.
     */
    private val currentProject: MutableState<Project?> = mutableStateOf(null)

    /**
     * All possible collections.
     */
    private val projects: MutableState<MutableList<Project>> = mutableStateOf(mutableListOf())

    object StorageProjectStateModal : Storage {

        /**
         * Current state of the modal [ModalPopupCreateOrEditProject] when creating the organization.
         */
        internal val expandedStateModalCreateOrEditProjectPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * State of the modal window, either creating a project or editing it.
         */
        internal val isEditStateModalCreateOrEditProjectPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * The initial object for editing and creating a project.
         */
        internal val initialProjectStateModal: MutableState<Project?> = mutableStateOf(null)

        /**
         * Set the state of the modal window - creating a project.
         *
         * @param value `Active` or `inactive`.
         */
        internal fun setExpandedStateModalCreateOrEditProjectPopup(value: Boolean) {
            expandedStateModalCreateOrEditProjectPopup.value = value
        }

        /**
         * Set the state of the modal window, either creating a project or editing it.
         *
         * @param value `Create` or `Edit`.
         */
        internal fun setIsEditStateModalCreateOrEditProjectPopup(value: Boolean) {
            isEditStateModalCreateOrEditProjectPopup.value = value
        }

        /**
         * Set up initial project.
         *
         * @param project Initial project
         */
        internal fun setInitialProjectStateModal(project: Project?) {
            initialProjectStateModal.value = project
        }

    }

    /**
     * Get the currently active project.
     *
     * @return Current active project.
     */
    internal fun getCurrentProject(): Project? {
        return currentProject.value
    }

    /**
     * Add projects.
     *
     * @param projects Projects.
     * @return Adds all the elements of the specified collection to the end of this list.
     */
    internal fun addAllProject(projects: Collection<Project>): Boolean {
        return this.projects.value.addAll(projects)
    }

    /**
     * Add project.
     *
     * @param project Project.
     */
    internal fun addProject(project: Project) {
        val newProjects: MutableList<Project> = mutableListOf()
        newProjects.addAll(projects.value)
        newProjects.add(project)

        this.projects.value = newProjects
    }

    /**
     * Install the project into the repository.
     *
     * @param project Project.
     */
    internal fun setProject(project: Project) {
        val newProjects: MutableList<Project> = this.projects.value.toMutableList()
        var indexSetting: Int = -1

        newProjects.forEachIndexed { index, newProject ->
            if (newProject.id == project.id) {
                indexSetting = index
            }
        }

        if (indexSetting != -1) {
            newProjects[indexSetting] = project
        }

        this.projects.value = newProjects
    }

    /**
     * Get all available projects.
     *
     * @return projects.
     */
    internal fun getProjects(): MutableState<MutableList<Project>> {
        return projects
    }

    fun setup(projects: Collection<Project>) {
        addAllProject(projects)
    }

    /**
     * Set current project as active.
     *
     * @param project Project.
     */
    internal fun setCurrentProject(project: Project?) {
        currentProject.value = project
    }

    /**
     * Get all available projects for the selected organization by ID.
     *
     * @param organizationID Organization ID.
     * @return Available projects.
     */
    internal fun getAllProjectByOrganizationID(organizationID: Long): Collection<Project> {
        return projects.value.filter { project: Project ->
            project.organizationID == organizationID
        }
    }

    /**
     * Get the project's latest unique identifier.
     *
     * @return Latest unique identifier.
     */
    internal fun getLastID(): Long {
        return getLastID(models = projects.value)
    }

    /**
     * Deletes a project by unique identifier.
     *
     * @param projectID Unique identification.
     */
    internal fun removeProject(projectID: Long) {
        projects.value = projects.value.filter { project: Project ->
            project.id != projectID
        }.toMutableList()
    }

}
