package com.zer0s2m.keeper.dto

data class CollectionProject(

    override val id: Long,

    val projectID: Long,

    val title: String

) : Model
