package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    private val collectionsProject: MutableState<MutableList<CollectionProject>> = mutableStateOf(mutableListOf())

    /**
     * Add collections.
     *
     * @param collectionsProject Collections.
     * @return Adds all the elements of the specified collection to the end of this list.
     */
    internal fun addAllCollectionsProject(collectionsProject: Collection<CollectionProject>): Boolean {
        return this.collectionsProject.value.addAll(collectionsProject)
    }

    fun setup(collectionsProject: Collection<CollectionProject>) {
        addAllCollectionsProject(collectionsProject)
    }

    internal fun getAllCollectionsProjectByProjectID(projectID: Long): Collection<CollectionProject> {
        return collectionsProject.value.filter { it.projectID == projectID }
    }

}
