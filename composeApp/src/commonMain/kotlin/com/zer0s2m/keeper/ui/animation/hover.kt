package com.zer0s2m.keeper.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
internal fun setHoverInComponent(
    interactionSource: MutableInteractionSource,
    isHover: MutableState<Boolean>
) {
    val interactions = remember { mutableStateListOf<Interaction>() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> {
                    interactions.add(interaction)
                    isHover.value = true
                }
                is HoverInteraction.Exit -> {
                    interactions.add(interaction)
                    isHover.value = false
                }
            }
        }
    }
}

@Composable
internal fun setAnimateColorAsStateInCard(
    isHover: MutableState<Boolean>,
    borderColor: Color = MaterialTheme.colors.primary,
    borderHover: Color = MaterialTheme.colors.onBackground
): State<Color> {
    return animateColorAsState(
        targetValue = if (isHover.value) borderColor else borderHover,
        animationSpec = tween(0, 0, LinearEasing)
    )
}
