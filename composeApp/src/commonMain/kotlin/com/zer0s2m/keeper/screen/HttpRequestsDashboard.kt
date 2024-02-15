package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.RightHttpRequestsPanel

/**
 * @param navigationController Controller for walking between screens.
 */
class HttpRequestsDashboard(override val navigationController: NavigationController) : BaseDashboard {

    private val httpRequests: MutableState<MutableList<HttpRequest>> = mutableStateOf(mutableListOf())

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = PADDING * 3)
                .fillMaxWidth()
        ) {
            Text(
                text = "HTTP Requests (${httpRequests.value.size})",
                fontSize = 14.sp,
                lineHeight = 0.sp
            )
        }

        RightHttpRequestsPanel(httpRequests = httpRequests)
    }

    /**
     * Set available Http requests to the selected active collection.
     *
     * @param projects Http requests.
     * @return Dashboard.
     */
    fun setProjects(projects: Collection<HttpRequest>): HttpRequestsDashboard {
        this.httpRequests.value.addAll(projects)
        return this
    }

}
