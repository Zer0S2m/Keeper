package com.zer0s2m.keeper.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zer0s2m.keeper.actions.ActionState
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.Organization
import com.zer0s2m.keeper.dto.Project
import com.zer0s2m.keeper.dto.State
import com.zer0s2m.keeper.enum.Config
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageOrganization
import com.zer0s2m.keeper.storage.StorageProject
import com.zer0s2m.keeper.storage.StorageState
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path

/**
 * Load configuration for organizations.
 */
internal fun loadStorageOrganization() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_ORGANIZATION.path)

    val data: String = File(Config.PATH_DB_ORGANIZATION.path)
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)
    val type: Type = object : TypeToken<Collection<Organization>>() {}.type
    val readyData: Collection<Organization> = Gson().fromJson(data, type)

    StorageOrganization.setup(readyData)
}

/**
 * Load configuration for projects.
 */
internal fun loadStorageProjects() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_PROJECT.path)

    val data: String = File(Config.PATH_DB_PROJECT.path)
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)
    val type: Type = object : TypeToken<Collection<Project>>() {}.type
    val readyData: Collection<Project> = Gson().fromJson(data, type)

    StorageProject.setup(readyData)
}

/**
 * Load configuration for collection.
 */
internal fun loadStorageCollection() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_COLLECTION.path)

    val data: String = File(Config.PATH_DB_COLLECTION.path)
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)
    val type: Type = object : TypeToken<Collection<CollectionProject>>() {}.type
    val readyData: Collection<CollectionProject> = Gson().fromJson(data, type)

    StorageCollectionProject.setup(readyData)
}

/**
 * Load configuration for state.
 */
internal fun loadStorageState() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_STATE.path)

    val data: String = File(Config.PATH_DB_STATE.path)
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)
    val readyData: State = Gson().fromJson(data, State::class.java)

    ActionState.setOrganization(readyData.organizationID)
    ActionState.setProject(readyData.projectID)
}

/**
 * Check for the existence of the configuration area.
 */
private fun checkExistsDirectoryConfig() {
    val pathDirectoryConfig: Path = Path.of(Config.PATH_DIRECTORY_CONFIG.path)
    if (!Files.exists(pathDirectoryConfig)) {
        Files.createDirectory(pathDirectoryConfig)
    }
}

/**
 * Check the configuration file for its existence.
 *
 * If the file is missing, it will be created.
 *
 * @param path Path to configuration file.
 */
private fun checkExistsFileDBDTO(path: String) {
    val pathDBFile: Path = Path.of(path)
    if (!Files.exists(pathDBFile)) {
        Files.createFile(pathDBFile)
    }
}

/**
 * Save the following application states:
 *
 * 1) Established active organization.
 * 2) Installed aquatic project.
 */
internal fun saveStorageState() {
    PrintWriter(FileWriter(Config.PATH_DB_STATE.path)).use {
        val gson = Gson()
        it.write(gson.toJson(StorageState.getStateDTO()))
    }
}

/**
 * Save organizations to a configuration file.
 */
internal fun saveStorageOrganization() {
    PrintWriter(FileWriter(Config.PATH_DB_ORGANIZATION.path)).use {
        val gson = Gson()
        it.write(gson.toJson(StorageOrganization.getAllOrganizations().value))
    }
}

/**
 * Save projects to a configuration file.
 */
internal fun saveStorageProject() {
    PrintWriter(FileWriter(Config.PATH_DB_PROJECT.path)).use {
        val gson = Gson()
        it.write(gson.toJson(StorageProject.getProjects().value))
    }
}

/**
 * Save collections to a configuration file.
 */
internal fun saveStorageCollection() {
    PrintWriter(FileWriter(Config.PATH_DB_COLLECTION.path)).use {
        val gson = Gson()
        it.write(gson.toJson(StorageCollectionProject.getCollectionsProject().value))
    }
}
