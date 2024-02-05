package com.zer0s2m.keeper.storage

import com.zer0s2m.keeper.dto.Project

/**
 * Basic storage for projects.
 */
object StorageProject {

    /**
     * Current selected project.
     */
    private val currentProject: Project? = null

    /**
     * All possible collections.
     */
    private val projects: MutableList<Project> = mutableListOf()

}
