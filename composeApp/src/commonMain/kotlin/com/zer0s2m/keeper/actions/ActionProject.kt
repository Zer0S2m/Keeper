package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState

/**
 * Action repository - projects.
 */
object ActionProject : Action {

    internal fun createProject() {
        println(true)
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