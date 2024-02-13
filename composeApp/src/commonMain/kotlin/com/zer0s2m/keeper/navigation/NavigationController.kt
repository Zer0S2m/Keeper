package com.zer0s2m.keeper.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Navigation controller for handling screen changes
 *
 * @param startDestination The current state of the screen from which transitions begin
 * @param backStackScreen Old screen states to navigate to them
 */
class NavigationController(
    private val startDestination: String,
    private var backStackScreen: MutableSet<String> = mutableSetOf()
) {

    /**
     * Current Screen State
     */
    var currentScreen: MutableState<String> = mutableStateOf(startDestination)

    fun navigate(route: String) {
        if (route != currentScreen.value) {
            if (backStackScreen.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreen.remove(currentScreen.value)
            }

            if (route == startDestination) {
                backStackScreen = mutableSetOf()
            } else {
                backStackScreen.add(currentScreen.value)
            }

            currentScreen.value = route
        }
    }

    fun navigateBack() {
        if (backStackScreen.isNotEmpty()) {
            currentScreen.value = backStackScreen.last()
            backStackScreen.remove(currentScreen.value)
        }
    }

}
