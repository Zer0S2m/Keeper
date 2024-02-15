package com.zer0s2m.keeper.actions

import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.storage.StorageHttpRequest

/**
 * Action repository - http requests.
 */
object ActionHttpRequest : Action {

    /**
     * Set new active http request.
     *
     * @param httpRequest Http request.
     */
    internal fun changeHttpRequest(httpRequest: HttpRequest) {
        StorageHttpRequest.setCurrentHttpRequest(httpRequest = httpRequest)
    }

}