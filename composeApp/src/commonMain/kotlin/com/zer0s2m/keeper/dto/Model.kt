package com.zer0s2m.keeper.dto

/**
 * The main entity of all models with a unique identifier.
 */
sealed interface Model {

    val id: Long

}
