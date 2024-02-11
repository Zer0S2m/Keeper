package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.ui.ModalPopupCreateCollection

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
     * Current state of the modal [ModalPopupCreateCollection] when creating the organization.
     */
    internal val expandedStateModalCreateCollectionPopup: MutableState<Boolean> = mutableStateOf(false)

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

    /**
     * Add collection.
     *
     * @param collectionProject Collection.
     */
    internal fun addCollectionsProject(collectionProject: CollectionProject) {
        val newCollectionsProject: MutableList<CollectionProject> = mutableListOf()
        newCollectionsProject.addAll(collectionsProject.value)
        newCollectionsProject.add(collectionProject)

        collectionsProject.value = newCollectionsProject
    }

    /**
     * Set the state of the modal window - creating a collection.
     *
     * @param value `Active` or `inactive`.
     */
    internal fun setExpandedStateModalCreateCollectionPopup(value: Boolean) {
        expandedStateModalCreateCollectionPopup.value = value
    }

    /**
     * Get the collection's latest unique identifier.
     *
     * @return Latest unique identifier.
     */
    internal fun getLastID(): Long {
        return getLastID(models = collectionsProject.value)
    }

    /**
     * Get all available collections.
     *
     * @return Available collections.
     */
    internal fun getCollectionsProject(): MutableState<MutableList<CollectionProject>> {
        return collectionsProject
    }

    /**
     * Delete all collections associated with the specified project in the form of `ID`.
     *
     * @param projectID Unique project identifier.
     */
    internal fun removeCollectionsProjectByProjectID(projectID: Long) {
        collectionsProject.value = collectionsProject.value.filter {
            it.projectID != projectID
        }.toMutableList()
    }

}
