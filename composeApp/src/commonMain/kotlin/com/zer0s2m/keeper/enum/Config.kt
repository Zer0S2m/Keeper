package com.zer0s2m.keeper.enum

/**
 * Configuration files for system operation.
 *
 * @param path Path to configuration file.
 */
enum class Config(val path: String) {

    PATH_DIRECTORY_CONFIG(System.getProperty("user.home") + "/.config/Keeper"),

    PATH_DB_ORGANIZATION(PATH_DIRECTORY_CONFIG.path + "/organization-db.json"),

    PATH_DB_PROJECT(PATH_DIRECTORY_CONFIG.path + "/project-db.json"),

    PATH_DB_COLLECTION(PATH_DIRECTORY_CONFIG.path + "/collection-db.json"),

    PATH_DB_HTTP_REQUEST(PATH_DIRECTORY_CONFIG.path + "/http-request-db.json"),

    PATH_DB_STATE(PATH_DIRECTORY_CONFIG.path + "/state-db.json")

}
