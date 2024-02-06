package com.zer0s2m.keeper.storage

import com.zer0s2m.keeper.dto.CollectionProject

/**
 * Basic storage for collections.
 */
object StorageCollectionProject : Storage {

    /**
     * Current selected collection.
     */
    private val currentCollectionProject: CollectionProject? = null

    /**
     * All available collections.
     */
    private val collectionsProject: MutableList<CollectionProject> = mutableListOf()

}