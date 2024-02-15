package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.WIDTH_RIGHT_PANEL
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.dto.HttpRequest
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.storage.StorageCollectionProject
import com.zer0s2m.keeper.storage.StorageHttpRequest
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.TopPanel

/**
 * @param navigationController Controller for walking between screens.
 */
class HttpDashboard(override val navigationController: NavigationController) : BaseDashboard {
    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Column {
            Column(
                modifier =
                    Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
            ) {
                TopPanel(navigationController = navigationController)
            }
            Divider(
                color = Color.Gray,
                modifier =
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth(),
            )
            Row {
                Column(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .width(WIDTH_RIGHT_PANEL + 60.dp)
                            .padding(PADDING * 2),
                ) {
                    StorageCollectionProject.getCurrentCollectionProject()
                        ?.let { collectionProject: CollectionProject ->
                            HttpRequestsDashboard(navigationController = navigationController)
                                .setProjects(
                                    StorageHttpRequest.getAllHttpRequestByCollectionProjectID(
                                        collectionProjectID = collectionProject.id,
                                    ),
                                )
                                .render()
                        }
                }
                Divider(
                    color = Color.Gray,
                    modifier =
                        Modifier
                            .width(1.dp)
                            .fillMaxHeight(),
                )

                StorageHttpRequest.getCurrentHttpRequest()?.let { httpRequest: HttpRequest ->
                    HttpRequestDashboard(navigationController = navigationController)
                        .setHttpRequest(httpRequest)
                        .render()
                }
            }
        }
    }
}
