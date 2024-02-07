package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.Project

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
     * Get the currently active project.
     *
     * @return Current active project.
     */
    internal fun getCurrentProject(): Project? {
        return currentProject.value
    }

    internal fun addAllProject(projects: Collection<Project>): Boolean {
        return this.projects.value.addAll(projects)
    }

    internal fun getProjects(): MutableState<MutableList<Project>> {
        return projects
    }

    internal fun setup(projects: Collection<Project>) {
        addAllProject(projects)
    }

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

}
