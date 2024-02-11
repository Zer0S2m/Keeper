package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.ui.ModalPopupCreateCollection

/**
 * Action repository - collection.
 */
object ActionCollectionProject : Action {

    /**
     * Open a modal window to create a collection..
     *
     * @param state State of the modal window. `Active` or `inactive`
     */
    internal fun openModalCreateCollectionProjectPopup(state: Boolean) {
        StorageCollectionProject.setExpandedStateModalCreateCollectionPopup(value = state)
    }

    /**
     * Cancel the creation the collection. Following steps when canceling:
     *
     * 1) Change the state of the modal window [ModalPopupCreateCollection] to `inactive`.
     */
    internal fun cancelCreateProject() {
        openModalCreateCollectionProjectPopup(state = false)
    }

    /**
     * Creating a new collection. Includes:
     *
     * 1) Change the state of the modal window [ModalPopupCreateCollection] to `inactive`.
     * 2) Adding a collection to the repository [StorageCollectionProject].
     *
     * @param collectionProject
     */
    internal fun createCollectionProject(collectionProject: CollectionProject) {
        openModalCreateCollectionProjectPopup(state = false)
        StorageCollectionProject.addCollectionsProject(collectionProject = collectionProject)
    }

}