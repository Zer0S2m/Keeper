package com.zer0s2m.keeper.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zer0s2m.keeper.actions.ActionCollectionProject
import com.zer0s2m.keeper.constant.PADDING
import com.zer0s2m.keeper.constant.SHAPE
import com.zer0s2m.keeper.dto.CollectionProject
import com.zer0s2m.keeper.navigation.NavigationController
import com.zer0s2m.keeper.ui.BaseDashboard
import com.zer0s2m.keeper.ui.ButtonAdd
import com.zer0s2m.keeper.ui.RightCollectionProjectPanel

/**
 * Screen for displaying available collections in a specific project.
 *
 * @param navigationController Controller for walking between screens.
 */
class CollectionProjectDashboard(override val navigationController: NavigationController) : BaseDashboard {

    private val collectionsProject: MutableState<MutableList<CollectionProject>> = mutableStateOf(mutableListOf())

    /**
     * Call screen render.
     */
    @Composable
    override fun render() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = PADDING * 3)
        ) {
            Text(
                text = "COLLECTIONS (${collectionsProject.value.size})",
                fontSize = 14.sp,
                lineHeight = 0.sp,
                modifier = Modifier.padding(end = PADDING)
            )
            ButtonAdd(
                modifier = Modifier
                    .height(20.dp)
                    .width(28.dp)
                    .padding(top = PADDING),
                modifierButton = Modifier
                    .height(20.dp)
                    .width(28.dp),
                shape = RoundedCornerShape(SHAPE),
                onClick = { ActionCollectionProject.openModalCreateCollectionProjectPopup(true) }
            )
        }

        RightCollectionProjectPanel(collectionsProject = collectionsProject)
    }

    /**
     * Set available collections to the selected active project.
     *
     * @param collectionsProject Collections.
     * @return Dashboard.
     */
    fun setProjects(collectionsProject: Collection<CollectionProject>): CollectionProjectDashboard {
        this.collectionsProject.value.addAll(collectionsProject)
        return this
    }

}
