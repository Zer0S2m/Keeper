package com.zer0s2m.keeper.storage

import com.zer0s2m.keeper.dto.Model

/**
 * Basic interface for storing the current application state of a separate area of each screen.
 */
interface Storage {

    /**
     * Get the model latest unique identifier.
     *
     * @param models Models.
     * @return Latest unique identifier.
     */
    fun getLastID(models: Collection<Model>): Long {
        if (models.isEmpty()) {
            return 0
        }

        var lastID: Long = 0

        models.forEach {
            if (it.id > lastID) {
                lastID = it.id
            }
        }

        return lastID
    }

}
