package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.TopPanelHttpRequest

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
        httpRequest.value?.let { httpRequest: HttpRequest ->
            Column {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TopPanelHttpRequest(httpRequest = httpRequest)
                }

                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(),
                )
            }
        }
    }

    internal fun setHttpRequest(httpRequest: HttpRequest): HttpRequestDashboard {
        this.httpRequest.value = httpRequest
        return this
    }

}