package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.HttpRequest

/**
 * Basic storage for http requests.
 */
object StorageHttpRequest : Storage {

    /**
     * Current selected http request.
     */
    private val currentHttpRequest: MutableState<HttpRequest?> = mutableStateOf(null)

    /**
     * All possible http request.
     */
    private val httpRequests: MutableState<MutableList<HttpRequest>> = mutableStateOf(mutableListOf())

    internal fun addAllHttpRequest(httpRequests: Collection<HttpRequest>) {
        this.httpRequests.value.addAll(httpRequests)
    }

    /**
     * Perform initial setup of the entire storage.
     *
     * @param httpRequests Http requests.
     */
    fun setup(httpRequests: Collection<HttpRequest>) {
        addAllHttpRequest(httpRequests)
    }

    /**
     * Get all available http requests for the selected collection by ID.
     *
     * @param collectionProjectID Collection ID.
     * @return Available http requests.
     */
    internal fun getAllHttpRequestByCollectionProjectID(collectionProjectID: Long): Collection<HttpRequest> {
        return httpRequests.value.filter { httpRequest: HttpRequest ->
            httpRequest.collectionID == collectionProjectID
        }
    }

    /**
     * Set new active http request.
     *
     * @param httpRequest Http request.
     */
    internal fun setCurrentHttpRequest(httpRequest: HttpRequest?) {
        currentHttpRequest.value = httpRequest
    }

    /**
     * Get active http request.
     *
     * @return Current http request.
     */
    internal fun getCurrentHttpRequest(): HttpRequest? {
        return currentHttpRequest.value
    }

}
