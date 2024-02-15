package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditCollection

/**
 * Basic storage for collections.
 */
object StorageCollectionProject : Storage {

    /**
     * Current selected collection.
     */
    private val currentCollectionProject: MutableState<CollectionProject?> = mutableStateOf(null)

    /**
     * All available collections.
     */
    private val collectionsProject: MutableState<MutableList<CollectionProject>> = mutableStateOf(mutableListOf())

    object StorageCollectionProjectStateModal : Storage {

        /**
         * The initial object for editing and creating a collection.
         */
        internal val initialCollectionProjectStateModal: MutableState<CollectionProject?> = mutableStateOf(null)

        /**
         * Current state of the modal [ModalPopupCreateOrEditCollection] when creating the organization.
         */
        internal val expandedStateModalCreateOrEditCollectionPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * State of the modal window, either creating a collection or editing it.
         */
        internal val isEditStateModalCreateOrEditCollectionPopup: MutableState<Boolean> = mutableStateOf(false)

        /**
         * Set the state of the modal window - creating a collection.
         *
         * @param value `Active` or `inactive`.
         */
        internal fun setExpandedStateModalCreateCollectionPopup(value: Boolean) {
            expandedStateModalCreateOrEditCollectionPopup.value = value
        }

        /**
         * Set the state of the modal window, either creating a collection or editing it.
         *
         * @param value `Create` or `Edit`.
         */
        internal fun setIsEditStateModalCreateOrEditCollectionPopup(value: Boolean) {
            isEditStateModalCreateOrEditCollectionPopup.value = value
        }

        /**
         * Set up initial collection project.
         *
         * @param collectionProject Initial collection.
         */
        internal fun setInitialCollectionProjectStateModal(collectionProject: CollectionProject?) {
            initialCollectionProjectStateModal.value = collectionProject
        }

    }

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

    /**
     * Deletes a collection by unique identifier.
     *
     * @param collectionProjectID Unique identification.
     */
    internal fun removeCollectionProject(collectionProjectID: Long) {
        collectionsProject.value = collectionsProject.value.filter { collectionProject: CollectionProject ->
            collectionProject.id != collectionProjectID
        }.toMutableList()
    }

    /**
     * Install the collection into the repository.
     *
     * @param collectionProject Collection.
     */
    internal fun setCollectionProject(collectionProject: CollectionProject) {
        val newCollectionsProject: MutableList<CollectionProject> = this.collectionsProject.value.toMutableList()
        var indexSetting: Int = -1

        newCollectionsProject.forEachIndexed { index, newProject ->
            if (newProject.id == collectionProject.id) {
                indexSetting = index
            }
        }

        if (indexSetting != -1) {
            newCollectionsProject[indexSetting] = collectionProject
        }

        this.collectionsProject.value = newCollectionsProject
    }

    internal fun setCurrentCollectionProject(collectionProject: CollectionProject?) {
        currentCollectionProject.value = collectionProject
    }

    internal fun getCurrentCollectionProject(): CollectionProject? {
        return currentCollectionProject.value;
    }

}
