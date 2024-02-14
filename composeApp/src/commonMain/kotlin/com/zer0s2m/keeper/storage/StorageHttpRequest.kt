package com.zer0s2m.keeper.storage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.HttpRequest

/**
 * Basic storage for http requests.
 */
object StorageHttpRequest : Storage {

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

}
