package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.ui.ModalPopupCreateProject

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

    /**
     * Current state of the modal [ModalPopupCreateProject] when creating the organization.
     */
    internal val expandedStateModalCreateProjectPopup: MutableState<Boolean> = mutableStateOf(false)

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
    internal fun addOrganization(project: Project) {
        val newProjects: MutableList<Project> = mutableListOf()
        newProjects.addAll(projects.value)
        newProjects.add(project)

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

    internal fun setup(projects: Collection<Project>) {
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
     * Set the state of the modal window - creating a project.
     *
     * @param value `Active` or `inactive`.
     */
    internal fun setExpandedStateModalCreateProjectPopup(value: Boolean) {
        expandedStateModalCreateProjectPopup.value = value
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
    fun getLastID(): Long {
        return getLastID(models = projects.value)
    }

}
