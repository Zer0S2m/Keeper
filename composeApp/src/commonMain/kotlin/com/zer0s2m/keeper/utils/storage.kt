package com.zer0s2m.keeper.utils

import com.zer0s2m.keeper.enum.Config
import java.nio.file.Files
import java.nio.file.Path

/**
 * Load configuration for organizations.
 */
internal fun loadStorageOrganization() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_ORGANIZATION.path)
}

/**
 * Load configuration for projects.
 */
internal fun loadStorageProjects() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_PROJECT.path)
}

/**
 * Load configuration for collection.
 */
internal fun loadStorageCollection() {
    checkExistsDirectoryConfig()
    checkExistsFileDBDTO(Config.PATH_DB_COLLECTION.path)
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