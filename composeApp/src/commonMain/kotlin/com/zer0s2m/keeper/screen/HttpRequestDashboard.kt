package com.zer0s2m.keeper.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard

/**
 * Screen - http request.
 *
 * @param navigationController Controller for walking between screens.
 */
class HttpRequestDashboard(override val navigationController: NavigationController) : BaseDashboard {

    private val httpRequest: MutableState<HttpRequest?> = mutableStateOf(null)

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Text(httpRequest.value!!.title)
    }

    internal fun setHttpRequest(httpRequest: HttpRequest): HttpRequestDashboard {
        this.httpRequest.value = httpRequest
        return this
    }

}