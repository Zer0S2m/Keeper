package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.ui.ModalPopupCreateOrEditCollection

/**
 * Action repository - collection.
 */
object ActionCollectionProject : Action {

    /**
     * Open a modal window to create a collection.
     *
     * @param state State of the modal window. `Active` or `inactive`.
     * @param isEdit `Create` or `Edit`.
     * @param collectionProject Initial collection.
     */
    internal fun openModalCreateOrEditCollectionProjectPopup(
        state: Boolean,
        isEdit: Boolean,
        collectionProject: CollectionProject?
    ) {
        StorageCollectionProject
            .StorageCollectionProjectStateModal
            .setExpandedStateModalCreateCollectionPopup(value = state)
        StorageCollectionProject
            .StorageCollectionProjectStateModal
            .setIsEditStateModalCreateOrEditCollectionPopup(value = isEdit)
        StorageCollectionProject
            .StorageCollectionProjectStateModal
            .setInitialCollectionProjectStateModal(collectionProject = collectionProject)
    }

    /**
     * Cancel the creation the collection. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditCollection] to `inactive`.
     */
    internal fun cancelCreateOrEditProject() {
        openModalCreateOrEditCollectionProjectPopup(state = false, isEdit = false, collectionProject = null)
    }

    /**
     * Creating a new collection. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditCollection] to `inactive`.
     * 2) Adding a collection to the repository [StorageCollectionProject].
     * 3) Sets the original project
     * [StorageCollectionProject.StorageCollectionProjectStateModal.initialCollectionProjectStateModal] to `null`.
     *
     * @param collectionProject
     */
    internal fun createCollectionProject(collectionProject: CollectionProject) {
        openModalCreateOrEditCollectionProjectPopup(state = false, isEdit = false, collectionProject = null)
        StorageCollectionProject.addCollectionsProject(collectionProject = collectionProject)
    }

    /**
     * Deleting a collection. Includes:
     *
     * 1) Delete a collection.
     *
     * @param collectionProjectID
     */
    internal fun deleteCollectionProject(collectionProjectID: Long) {
        StorageCollectionProject.removeCollectionProject(collectionProjectID = collectionProjectID)
    }

    /**
     * Editing a collection. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateOrEditCollection] to `inactive`.
     * 2) Settings an edited collection to the repository [StorageCollectionProject].
     * 3) Sets the original collection
     * [StorageCollectionProject.StorageCollectionProjectStateModal.initialCollectionProjectStateModal] to `null`
     *
     * @param collectionProject Replacement collection in the repository.
     */
    internal fun editCollection(collectionProject: CollectionProject) {
        openModalCreateOrEditCollectionProjectPopup(state = false, isEdit = false, collectionProject = null)
        StorageCollectionProject.setCollectionProject(collectionProject = collectionProject)
    }

}