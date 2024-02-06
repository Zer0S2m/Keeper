package com.zer0s2m.keeper.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import com.zer0s2m.keeper.enum.Icon
import com.zer0s2m.keeper.utils.openUrl

@Composable
fun TopPanel() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        ButtonGitHub(
            modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand),
            onClick = {
                openUrl("https://github.com/Zer0S2m")
            }
        ) {
            Image(
                painter = painterResource(resourcePath = Icon.ICON_GITHUB.path),
                contentDescription = "GitHub icon"
            )
        }
    }
}

